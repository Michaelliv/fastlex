package com.github.michaelliv.characterbuffer.impl

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class TestCharacterBuffer {
    @Test
    fun `test advance char`() {
        val cb = CharacterBuffer.fromString("this")
        assertEquals(NULL_CHAR, cb.prevChar)
        assertEquals('t', cb.curChar)
        cb.advanceChar()
        assertEquals('t', cb.prevChar)
        assertEquals('h', cb.curChar)
        cb.advanceChar()
        assertEquals('h', cb.prevChar)
        assertEquals('i', cb.curChar)
        cb.advanceChar()
        assertEquals('i', cb.prevChar)
        assertEquals('s', cb.curChar)
        cb.advanceChar()
        assertEquals('s', cb.prevChar)
        assertEquals(NULL_CHAR, cb.curChar)
        cb.advanceChar()
        assertEquals(NULL_CHAR, cb.prevChar)
        assertEquals(NULL_CHAR, cb.curChar)
    }

    @Test
    fun `test has more chars and no more chars`() {
        val cb = CharacterBuffer.fromString("this")
        assertTrue { cb.hasMoreChars() }
        assertFalse { cb.noMoreChars() }
        cb.setPosition(cb.lastIndex + 1)
        assertTrue { cb.noMoreChars() }
        assertFalse { cb.hasMoreChars() }
    }

    @Test
    fun `test advance if and while`() {
        val cb = CharacterBuffer.fromString("this123abc")
        cb.advanceCharIf { it.isLetter() }
        assertEquals(1, cb.curIndex)
        assertEquals('h', cb.curChar)
        assertEquals('t', cb.prevChar)

        cb.advanceCharWhile(Char::isDigit)
        assertEquals(1, cb.curIndex)
        assertEquals('h', cb.curChar)
        assertEquals('t', cb.prevChar)

        cb.advanceCharWhile(Char::isLetter)
        assertEquals(4, cb.curIndex)
        assertEquals('1', cb.curChar)
        assertEquals('s', cb.prevChar)

        val steps = cb.advanceCharWhile(Char::isDigit, Char::isLetter)
        assertTrue { cb.noMoreChars() }

        assertTrue(
            cb.curIndex == cb.lastIndex
                    && cb.curChar == NULL_CHAR
                    && cb.prevChar == 'c'
                    && steps.first == 3
                    && steps.second == 3
        )
    }

    @Test
    fun `test peek forward and peek backwards if and while`() {
        val cb = CharacterBuffer.fromString("this123abc")
        val nextChar = cb.peekForward()
        assertTrue { cb.curChar == 't' && nextChar == 'h' }
    }
}