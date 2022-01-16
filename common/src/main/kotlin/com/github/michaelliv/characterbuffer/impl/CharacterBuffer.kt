package com.github.michaelliv.characterbuffer.impl

import com.github.michaelliv.characterbuffer.ICharacterBuffer
import com.github.michaelliv.trie.impl.Trie
import com.github.michaelliv.trie.impl.Node

const val NULL_CHAR = '\u0000'


class CharacterBuffer(charArray: CharArray) : ICharacterBuffer {
    val arr = charArray
    override val lastIndex: Int = arr.lastIndex
    override var curIndex: Int = 0

    var prevChar: Char = NULL_CHAR
        private set
    var curChar: Char = arr[0]
        private set

    override fun setPosition(index: Int) {
        curIndex = index
        curChar = if (curIndex > lastIndex) {
            curIndex = lastIndex
            NULL_CHAR
        } else arr[curIndex]
        prevChar = if (curIndex == 0) NULL_CHAR else arr[curIndex - 1]
    }

    override fun hasMoreChars() = curChar != NULL_CHAR
    override fun noMoreChars() = curChar == NULL_CHAR
    override fun peekForward(i: Int): Char = arr[curIndex + i]

    override fun advanceChar() {
        prevChar = curChar
        curChar = if (curIndex < lastIndex) arr[++curIndex] else NULL_CHAR
    }

    override fun advanceCharIf(predicate: (Char) -> Boolean): Boolean {
        if (noMoreChars()) return false
        if (!predicate(curChar)) return false
        advanceChar()
        return true
    }

    override fun advanceCharWhile(predicate: (Char) -> Boolean): Int {
        var advanceCount = 0
        while (predicate(curChar) && hasMoreChars()) {
            advanceCount++
            advanceChar()
        }
        return advanceCount
    }

    override fun advanceCharWhile(p1: (Char) -> Boolean, p2: (Char) -> Boolean): Pair<Int, Int> {
        var p1Count = 0
        var p2Count = 0

        while (true) {
            if (p1(curChar)) {
                p1Count++
                if (hasMoreChars()) {
                    advanceChar()
                    continue
                } else break
            } else if (p2(curChar)) {
                p2Count++
                if (hasMoreChars()) {
                    advanceChar()
                    continue
                } else break
            } else {
                break
            }
        }
        return Pair(p1Count, p2Count)
    }

    override fun advanceCharWhile(
        p1: (Char) -> Boolean,
        p2: (Char) -> Boolean,
        p3: (Char) -> Boolean
    ): Triple<Int, Int, Int> {
        var p1Count = 0
        var p2Count = 0
        var p3Count = 0

        while (true) {
            if (p1(curChar)) {
                p1Count++
                if (hasMoreChars()) {
                    advanceChar()
                    continue
                } else break
            } else if (p2(curChar)) {
                p2Count++
                if (hasMoreChars()) {
                    advanceChar()
                    continue
                } else break
            } else if (p3(curChar)) {
                p3Count++
                if (hasMoreChars()) {
                    advanceChar()
                    continue
                } else break
            } else {
                break
            }
        }
        return Triple(p1Count, p2Count, p3Count)
    }

    override fun indexOf(element: Char, end: Int): Int {
        for (index in curIndex..end) {
            if (element == arr[index]) {
                return index
            }
        }
        return -1
    }

    override fun indexOf(end: Int, predicate: (Char) -> Boolean): Int {
        for (index in curIndex..end) {
            if (predicate(arr[index])) {
                return index
            }
        }
        return -1
    }

    companion object {
        fun fromString(string: String): CharacterBuffer = CharacterBuffer(string.toCharArray())
    }
}

data class TrieMatch(
    val isWord: Boolean,
    val wordLength: Int
)

fun CharacterBuffer.advanceCharWhileInTrie(trie: Trie): TrieMatch {
    var advanceCount = 0
    var subNode: Node = trie.root
    while (hasMoreChars()) {
        if (curChar !in subNode) break
        subNode = subNode.childNodes[curChar]!!
        advanceCount++
        advanceChar()
    }
    return TrieMatch(subNode.isWord, advanceCount)
}
