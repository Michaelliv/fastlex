package com.github.michaelliv.lexicon.impl

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue


internal class WordTest {
    @Test
    fun `test word matcher`() {
        val matcher = Word()
        assertTrue(matcher.isMatch("word"))
        assertTrue(matcher.isMatch("first-second"))
        assertFalse(matcher.isMatch("word123"))

        assertEquals("first", matcher.matchOrNull("first second"))
        assertEquals("first-second", matcher.matchOrNull("first-second"))
        assertEquals("first-second", matcher.matchOrNull("first-second!!??!"))
        assertEquals("someword", matcher.matchOrNull("someword!"))
        assertEquals("somewor'd", matcher.matchOrNull("somewor'd"))

        assertNull(matcher.matchOrNull("123"))
        assertNull(matcher.matchOrNull("word-123"))
        assertNull(matcher.matchOrNull("word_123"))
        assertNull(matcher.matchOrNull("word123"))
        assertNull(matcher.matchOrNull("123word"))
        assertNull(matcher.matchOrNull("123word123"))
        assertNull(matcher.matchOrNull("some_word"))
        assertNull(matcher.matchOrNull("?someword"))
    }

}
