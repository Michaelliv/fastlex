package com.github.michaelliv.characterbuffer

interface ICharacterBuffer {
    val lastIndex: Int
    var curIndex: Int
    fun setPosition(index: Int)
    fun hasMoreChars(): Boolean
    fun noMoreChars(): Boolean
    fun peekForward(i: Int = 1): Char
    fun advanceChar()
    fun advanceCharIf(predicate: (Char) -> Boolean): Boolean
    fun advanceCharWhile(predicate: (Char) -> Boolean): Int
    fun advanceCharWhile(p1: (Char) -> Boolean, p2: (Char) -> Boolean): Pair<Int, Int>
    fun advanceCharWhile(p1: (Char) -> Boolean, p2: (Char) -> Boolean, p3: (Char) -> Boolean): Triple<Int, Int, Int>
    fun indexOf(element: Char, end: Int = lastIndex): Int
    fun indexOf(end: Int = lastIndex, predicate: (Char) -> Boolean): Int
}