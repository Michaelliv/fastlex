package com.github.michaelliv.lossycounter.impl

import com.github.michaelliv.lossycounter.StreamCounter
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong
import kotlin.math.absoluteValue

class StringLossyCounter(
    override val iterationSize: Int,
    override val perIterationCutoff: Int,
    concurrency: Int = Runtime.getRuntime().availableProcessors() * 8
) : StreamCounter<String, Object2LongOpenHashMap<String>> {

    override val gcCounter = AtomicInteger(0)
    override val currentStep = AtomicLong(0)

    private val counters = Array(concurrency) { Object2LongOpenHashMap<String>() }

    override fun add(value: String, amount: Long) {
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

    override fun complete(): Object2LongOpenHashMap<String> {
        println("Running complete")
        performGC((currentStep.get() / iterationSize).toInt() * perIterationCutoff)
        val result = Object2LongOpenHashMap<String>(counters.sumBy { it.size })
        counters.forEach { result.putAll(it); it.clear() }
        currentStep.set(0)
        println("Done complete, finished with ${result.size} results")
        return result
    }

    override fun performGC(min: Int) {
        println("Thread - ${Thread.currentThread().id} started GC")
        for (counter in counters) {
            val temp = Object2LongOpenHashMap<String>(counter.size)
            synchronized(counter) {
                for ((k, v) in counter.object2LongEntrySet().fastIterator()) {
                    if (v >= min) temp[k] = v
                }
                counter.clear()
                counter.putAll(temp)
            }
        }
    }

    override fun noStepAdd(value: String, amount: Long) {
        val step = currentStep.get()

        if (step % iterationSize == 0L) {
            step()
            gcCounter.incrementAndGet()
            performGC((step / iterationSize).toInt() * perIterationCutoff)
        }

        val mapIndex = value.hashCode().absoluteValue % counters.size
        val map = counters[mapIndex]
        synchronized(map) {
            map.addTo(value, amount)
        }
    }

}