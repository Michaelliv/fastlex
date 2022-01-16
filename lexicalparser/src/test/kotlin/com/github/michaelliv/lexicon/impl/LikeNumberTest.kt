package com.github.michaelliv.lexicon.impl

import com.github.michaelliv.lexicalparser.impl.LexicalParser
import com.github.michaelliv.lexicon.BASE_LEXICON
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


internal class LikeNumberTest {
    @Test
    fun `test non-numerics`() {
        val marker = LikeNumber()
        assertNull(marker.matchOrNull("-"))
        assertNull(marker.matchOrNull("a"))
        assertNull(marker.matchOrNull("abc"))
        assertNull(marker.matchOrNull("-a"))
        assertNull(marker.matchOrNull("~a"))
        assertNull(marker.matchOrNull("abc"))
        assertNull(marker.matchOrNull("!!"))
        assertNull(marker.matchOrNull("   "))
    }

    @Test
    fun `test numerics`() {
        val marker = LikeNumber()
        assertEquals("6.0.1", marker.matchOrNull("6.0.1"))
        assertEquals("--1", marker.matchOrNull("--1"))
        assertEquals("1", marker.matchOrNull("1"))
        assertEquals("-15", marker.matchOrNull("-15"))
        assertEquals("-0.5", marker.matchOrNull("-0.5"))
        assertEquals("+15", marker.matchOrNull("+15"))
        assertEquals("~15", marker.matchOrNull("~15"))
        assertEquals("100", marker.matchOrNull("100"))
        assertEquals("10_000", marker.matchOrNull("10_000"))
        assertEquals("10_000_0", marker.matchOrNull("10_000_0"))
        assertEquals("1,000", marker.matchOrNull("1,000"))
        assertEquals("1,000", marker.matchOrNull("1,000"))
        assertEquals("10.5", marker.matchOrNull("10.5"))
        assertEquals("0.1%", marker.matchOrNull("0.1%"))
        assertEquals("0.1", marker.matchOrNull("0.1 "))
        assertEquals("100%", marker.matchOrNull("100%"))
        assertEquals("100%", marker.matchOrNull("100% "))
        assertEquals("100%.", marker.matchOrNull("100%."))
        assertEquals("100%,", marker.matchOrNull("100%,"))
        assertEquals("1,000", marker.matchOrNull("1,000 "))
        assertEquals("1,000", marker.matchOrNull("1,000!!"))
        assertEquals("1.", marker.matchOrNull("1."))
        assertEquals("1.-", marker.matchOrNull("1.-"))
        assertEquals("100%,", marker.matchOrNull("100%,!"))
    }

    @Test
    fun `test edge cases`() {
        val parser = LexicalParser(LikeNumber(), *BASE_LEXICON)
        val result = parser.parse(" 8.4.25.771 armV7").toList()
        assertEquals(result[1].lexicon, LIKE_NUMBER)
        assertEquals(result[1].indexes, 1..10)
    }
}
