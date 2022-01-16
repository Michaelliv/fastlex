package com.github.michaelliv.lexicon.impl

import org.junit.Test
import kotlin.test.assertEquals


internal class ISO8601Test {
    @Test
    fun testIsMatch() {
        val matcher = ISO8601()
        assertEquals("2022-01-06", matcher.matchOrNull("2022-01-06"))
        assertEquals("2022-01-06", matcher.matchOrNull("2022-01-06 "))
        assertEquals("2022-01-06", matcher.matchOrNull("2022-01-06T"))
        assertEquals("2022-01-06", matcher.matchOrNull("2022-01-06T "))
        assertEquals("2022-01-06T08", matcher.matchOrNull("2022-01-06T08"))
        assertEquals("2022-01-06T08", matcher.matchOrNull("2022-01-06T08:"))
        assertEquals("2022-01-06T08:42", matcher.matchOrNull("2022-01-06T08:42"))
        assertEquals("2022-01-06T08:42", matcher.matchOrNull("2022-01-06T08:42:"))
        assertEquals("2022-01-06T08:42:27", matcher.matchOrNull("2022-01-06T08:42:27"))
        assertEquals("2022-01-06T08:42:27", matcher.matchOrNull("2022-01-06T08:42:27."))
        assertEquals("2022-01-06T08:42:27.549090", matcher.matchOrNull("2022-01-06T08:42:27.549090"))
        assertEquals("2022-01-06 08:42:27.549090", matcher.matchOrNull("2022-01-06 08:42:27.549090"))
        assertEquals("2022-01-06 08:42:27.549090", matcher.matchOrNull("2022-01-06 08:42:27.549090+"))
        assertEquals("2022-01-06 08:42:27.549090", matcher.matchOrNull("2022-01-06 08:42:27.549090-"))
        assertEquals("2022-01-06 08:42:27.549090+02", matcher.matchOrNull("2022-01-06 08:42:27.549090+02"))
        assertEquals("2022-01-06 08:42:27.549090+02:02", matcher.matchOrNull("2022-01-06 08:42:27.549090+02:02"))
        assertEquals("2022-01-06 08:42:27.549090-02:02", matcher.matchOrNull("2022-01-06 08:42:27.549090-02:02"))
        assertEquals("2022-01-06 08:42:27.549090", matcher.matchOrNull("2022-01-06 08:42:27.549090-a"))
        assertEquals("2022-01-06 08:42:27", matcher.matchOrNull("2022-01-06 08:42:27+ "))
        assertEquals("2022-01-06 08:42:27", matcher.matchOrNull("2022-01-06 08:42:27+-"))
    }
}