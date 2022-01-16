package com.github.michaelliv.lexicon.impl

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class MentionTest {
    @Test
    fun testIsMatch() {
        val ht = Mention()
        assertTrue { ht.isMatch("@mention") }
        assertTrue { ht.isMatch("@men_tion") }
        assertFalse { ht.isMatch("#hashtag") }
        assertEquals("@mention", ht.matchOrNull("@mention@mention"))
        assertEquals("@mention", ht.matchOrNull("@mention @mention"))
        assertEquals("@mention", ht.matchOrNull("@mention!!"))
        assertEquals("@mention123", ht.matchOrNull("@mention123!!"))
        assertEquals("@ment", ht.matchOrNull("@ment...ion123!!"))
    }
}