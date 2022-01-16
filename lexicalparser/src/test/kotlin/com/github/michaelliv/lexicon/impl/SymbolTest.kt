package com.github.michaelliv.lexicon.impl

import com.github.michaelliv.characters.ASCII
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


internal class SymbolTest {
    @Test
    fun `test symbol marker`() {
        val matcher = Symbol()
        ASCII.letters.forEach {
            assertNull(matcher.matchOrNull(it.toString()))
        }
        ASCII.digits.forEach {
            assertNull(matcher.matchOrNull(it.toString()))
        }
        ASCII.symbols.forEach {
            assertEquals(matcher.matchOrNull(it.toString()), it.toString())
        }
        assertEquals(matcher.matchOrNull("? "), "?")
        assertNull(matcher.matchOrNull(" ?"))
    }
}
