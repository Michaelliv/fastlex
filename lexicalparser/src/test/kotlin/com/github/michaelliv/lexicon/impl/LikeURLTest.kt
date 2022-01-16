package com.github.michaelliv.lexicon.impl

import org.junit.Test
import kotlin.test.assertTrue

internal class LikeURLTest {
    @Test
    fun `test should match`() {
        val lexicon = LikeURL()
        assertTrue { lexicon.isMatch("https://t.co/2Ecdop6zl1") }
    }
}