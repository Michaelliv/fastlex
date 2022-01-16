package com.github.michaelliv.lexicon.impl

import com.github.michaelliv.characters.ASCII
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


internal class DigitTest {
    @Test
    fun `test digit marker`() {
        val pattern = Digit()
        ASCII.letters.forEach {
            assertNull(pattern.matchOrNull(it.toString()))
        }
        ASCII.digits.forEach {
            assertEquals(pattern.matchOrNull(it.toString()), it.toString())
        }
        ASCII.symbols.forEach {
            assertNull(pattern.matchOrNull(it.toString()))
        }
        assertEquals(pattern.matchOrNull("1 "), "1")
        assertNull(pattern.matchOrNull(" 1"))
    }
}
