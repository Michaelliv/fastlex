package com.github.michaelliv.extensions

import it.unimi.dsi.fastutil.chars.CharOpenHashSet


infix fun CharRange.union(other: CharRange): CharOpenHashSet {
    val thisRef = this
    return buildCharSet {
        addAll(thisRef)
        addAll(other)
    }
}

fun Char.isSymbol() = !this.isLetterOrDigit() && !this.isWhitespace()
fun Iterable<Char>.toCharSet() = CharOpenHashSet(this.toCollection(HashSet()))

infix fun CharOpenHashSet.charSetUnion(other: CharOpenHashSet): CharOpenHashSet {
    val thisRef = this
    return buildCharSet {
        addAll(thisRef)
        addAll(other)
    }
}

infix fun CharOpenHashSet.charSetUnion(char: Char): CharOpenHashSet {
    val thisRef = this
    return buildCharSet {
        addAll(thisRef)
        add(char)
    }
}

infix fun CharOpenHashSet.charSetUnion(chars: CharSequence): CharOpenHashSet {
    val thisRef = this
    return buildCharSet {
        addAll(thisRef)
        addAll(chars.toList())
    }
}

fun openCharSetOf(vararg c: Char) = CharOpenHashSet(c)

fun buildCharSet(block: CharOpenHashSet.() -> Unit): CharOpenHashSet {
    val charSet = CharOpenHashSet()
    block(charSet)
    return charSet
}