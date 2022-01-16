package com.github.michaelliv.lexicon.impl

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


internal class LikeEmailTest {
    @Test
    fun `test values should match`() {
        val marker = LikeEmail()
        listOf(
            "prettyandsimple@example.com",
            "PRETTYANDSIMPLE@EXAMPLE.COM",
            "very.common@example.com",
            "disposable.style.email.with+symbol@example.com",
            "other.email-with-dash@example.com",
            "other.email_with-dash@example.com",
            "other.email_with-dash@exa-mple.com",
            "x@example.com",
            "example-indeed@strange-example.com",
        ).forEach {
            assertEquals(it, marker.matchOrNull(it))
        }

        assertEquals("prettyandsimple@example.com", marker.matchOrNull("prettyandsimple@example.com "))
    }

    @Test
    fun `test values should not match`() {
        val marker = LikeEmail()
        listOf(
            "\"prettyandsimple@example.com",
            "prettyandsimple.@example",
            "prettyandsimple@example",
//            "prettyandsimple@example...",
            "prettyand",
            "prettyandsimple@examp_le.c",
            "prettyandsimple\"@example.com",
            "prettyand\"simple@example.com",
            "prettyandsimple.example.co_m",
        ).forEach {
            assertNull(marker.matchOrNull(it), it)
        }
    }
}
