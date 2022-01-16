package com.github.michaelliv.lexicon.impl

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


internal class HashtagTest {
    @Test
    fun testIsMatch() {
        val ht = Hashtag()
        assertTrue { ht.isMatch("#hashtag") }
        assertTrue { ht.isMatch("#hash_tag") }
        assertFalse { ht.isMatch("@mention") }
        assertEquals("#Hashtag", ht.matchOrNull("#Hashtag#Hashtag"))
        assertEquals("#Hashtag", ht.matchOrNull("#Hashtag #Hashtag"))
        assertEquals("#Hashtag", ht.matchOrNull("#Hashtag!!"))
        assertEquals("#Hashtag123", ht.matchOrNull("#Hashtag123!!"))
        assertEquals("#Hash", ht.matchOrNull("#Hash...tag123!!"))
    }
}