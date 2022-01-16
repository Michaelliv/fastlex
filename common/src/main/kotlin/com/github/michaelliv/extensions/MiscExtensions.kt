package com.github.michaelliv.extensions

import java.io.FileInputStream
import java.util.zip.GZIPInputStream

inline fun <reified T> Any?.cast(): T? = this as? T
fun FileInputStream.gzipStream() = GZIPInputStream(this)
fun <T> List<T>.takeRandom(n: Int) = this.asSequence().shuffled().take(n).toList()
inline infix fun <reified T> Array<T>.concat(element: T) = this.toMutableList().also { it.add(element) }.toTypedArray()
