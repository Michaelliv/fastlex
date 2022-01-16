package com.github.michaelliv.lexicon.impl

import org.junit.Test
import kotlin.test.assertTrue

internal class CurrencyTest {
    @Test
    fun `test currency should match - number before currency name`() {
        val matcher = Currency()
        assertTrue { matcher.isMatch("5 Cents") }
        assertTrue { matcher.isMatch("5 cents") }
        assertTrue { matcher.isMatch("5 CENTS") }
        assertTrue { matcher.isMatch("5 USD") }
        assertTrue { matcher.isMatch("5 Dollars") }
        assertTrue { matcher.isMatch("5.4 Dollars") }
        assertTrue { matcher.isMatch("5,000 Dollars") }
        assertTrue { matcher.isMatch("$$$") }
    }

    @Test
    fun `test currency should not match`() {

    }
}