package com.github.michaelliv.lossycounter

import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong


interface StreamCounter<T, K> {
    val gcCounter: AtomicInteger
    val currentStep: AtomicLong
    val iterationSize: Int
    val perIterationCutoff: Int

    fun add(value: T, amount: Long)
    fun noStepAdd(value: T, amount: Long = 1L)
    fun step(): Long = currentStep.incrementAndGet()
    fun complete(): K
    fun performGC(min: Int)
}
