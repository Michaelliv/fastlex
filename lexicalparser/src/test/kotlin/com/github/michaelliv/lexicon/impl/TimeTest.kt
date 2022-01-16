package com.github.michaelliv.lexicon.impl

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class TimeTest {
    @Test
    fun `test time should match`() {
        val lexicon = Time()
        assertTrue { lexicon.isMatch("1:26") }
        assertTrue { lexicon.isMatch("7:20:") }
        assertTrue { lexicon.isMatch("7:20") }
        assertEquals("8:05 PM", lexicon.matchOrNull("8:05 PM-8:55 PM"))
        assertEquals("2:30", lexicon.matchOrNull("2:30-5:30"))
        assertEquals("5:20", lexicon.matchOrNull("5:20)"))
        assertEquals("04:01", lexicon.matchOrNull("04:01"))
        assertEquals("04:01", lexicon.matchOrNull("04:01 A-M"))
        assertEquals("04:01", lexicon.matchOrNull("04:01 P/M"))
        assertEquals("04:01 AM", lexicon.matchOrNull("04:01 AM"))
        assertEquals("04:01AM", lexicon.matchOrNull("04:01AM"))
        assertEquals("04:01am", lexicon.matchOrNull("04:01am"))
        assertEquals("04:01 a.m.", lexicon.matchOrNull("04:01 a.m."))
        assertEquals("04:01 p.m.", lexicon.matchOrNull("04:01 p.m."))
    }

    @Test
    fun `test time should not match`() {
        val lexicon = Time()
        assertFalse { lexicon.isMatch("3") }
        assertFalse { lexicon.isMatch("2") }
        assertFalse { lexicon.isMatch("1:1-3") }
        assertFalse { lexicon.isMatch("1:5") }
    }
}