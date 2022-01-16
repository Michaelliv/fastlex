package com.github.michaelliv.lexicon.impl

import org.junit.Test
import kotlin.test.assertTrue

internal class OtherTest {
    @Test
    fun testIsMatch() {
        val other = Other()
        // Matches everything
        assertTrue(other.isMatch("a"))
        assertTrue(other.isMatch("1"))
        assertTrue(other.isMatch(" "))
        assertTrue(other.isMatch("["))
    }
}