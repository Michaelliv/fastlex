package com.github.michaelliv.extensions

import com.github.michaelliv.hashing.murmurhash3
import it.unimi.dsi.fastutil.chars.CharOpenHashSet
import java.io.File
import java.io.FileInputStream
import java.util.*

fun String.toCharSet() = CharOpenHashSet(this.toCharArray())
fun String.longHash() = murmurhash3.hashUnencodedChars(this).padToLong()
fun CharSequence.longHash() = murmurhash3.hashUnencodedChars(this).padToLong()
fun String.inputStream() = FileInputStream(this)
fun String.toFile() = File(this)
fun String.capitalCase() = replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
