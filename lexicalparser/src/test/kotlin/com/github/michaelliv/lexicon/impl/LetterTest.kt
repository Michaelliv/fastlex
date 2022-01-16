package com.github.michaelliv.lexicon.impl

import com.github.michaelliv.characters.ASCII
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


internal class LetterTest {
    @Test
    fun `test letter marker`() {
        val pattern = Letter()
        ASCII.letters.forEach {
            assertEquals(it.toString(), pattern.matchOrNull(it.toString()))
        }
        ASCII.digits.forEach {
            assertNull(pattern.matchOrNull(it.toString()))
        }
        ASCII.symbols.forEach {
            assertNull(pattern.matchOrNull(it.toString()))
        }
        assertEquals(pattern.matchOrNull("a "), "a")
        assertNull(pattern.matchOrNull(" a"))
    }
}