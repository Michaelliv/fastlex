package com.github.michaelliv.benchmark

import kotlin.time.ExperimentalTime
import kotlin.time.measureTime


@OptIn(ExperimentalTime::class)
fun bench(
    title: String,
    testIterations: Int = 10000,
    warmUpIterations: Int = 100,
    action: () -> Unit
): Double {
    val results = ArrayList<Long>()
    for (iterationCount in 1..(testIterations + warmUpIterations)) {
        val timePassed = measureTime { action() }
        if (iterationCount > warmUpIterations) {
            results.add(timePassed.inWholeMicroseconds)
        }
    }
    val mean = results.average()
    println("$title,$mean")

    return mean
}
