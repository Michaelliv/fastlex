package com.github.michaelliv.lossycounter.impl

import com.github.michaelliv.lossycounter.StreamCounter
import it.unimi.dsi.fastutil.longs.Long2LongOpenHashMap
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong
import kotlin.math.absoluteValue

class LongLossyCounter(
    override val iterationSize: Int,
    override val perIterationCutoff: Int,
    concurrency: Int = Runtime.getRuntime().availableProcessors() * 8
) : StreamCounter<Long, Long2LongOpenHashMap> {

    override val gcCounter = AtomicInteger(0)
    override val currentStep = AtomicLong(0)

    private val counters = Array(concurrency) { Long2LongOpenHashMap() }

    override fun add(value: Long, amount: Long) {
        val step = currentStep.incrementAndGet()

        if (step % iterationSize == 0L) {
            gcCounter.incrementAndGet()
            performGC((step / iterationSize).toInt() * perIterationCutoff)
        }

        val mapIndex = value.hashCode().absoluteValue % counters.size
        val map = counters[mapIndex]

        synchronized(map) {
            map.addTo(value, amount)
        }
    }

    override fun noStepAdd(value: Long, amount: Long) {
        val step = currentStep.get()
        if (step % iterationSize == 0L) {
            gcCounter.incrementAndGet()
            performGC((step / iterationSize).toInt() * perIterationCutoff)
            step()
        }

        val mapIndex = value.hashCode().absoluteValue % counters.size
        val map = counters[mapIndex]
        synchronized(map) {
            map.addTo(value, amount)
        }
    }

    override fun complete(): Long2LongOpenHashMap {
        performGC((currentStep.get() / iterationSize).toInt() * perIterationCutoff)
        val result = Long2LongOpenHashMap(counters.sumBy { it.size })
        counters.forEach { result.putAll(it); it.clear() }
        currentStep.set(0)
        return result
    }

    override fun performGC(min: Int) {
        println { "Thread - ${Thread.currentThread().id} started GC" }
        for (counter in counters) {
            val temp = Long2LongOpenHashMap(counter.size)
            synchronized(counter) {
                for ((k, v) in counter.long2LongEntrySet().fastIterator()) {
                    if (v >= min) temp[k] = v
                }
                counter.clear()
                counter.putAll(temp)
            }
        }
    }

}