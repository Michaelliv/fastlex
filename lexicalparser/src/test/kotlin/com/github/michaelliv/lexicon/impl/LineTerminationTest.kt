package com.github.michaelliv.lexicon.impl

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


internal class LineTerminationTest {
    @Test
    fun `test line termination marker`() {
        val matcher = LineTermination()
        assertNull(matcher.matchOrNull(" "))
        assertNull(matcher.matchOrNull("  "))
        assertNull(matcher.matchOrNull("  ."))
        assertNull(matcher.matchOrNull("  a"))
        assertNull(matcher.matchOrNull("  \n"))
        assertNull(matcher.matchOrNull("\t"))
        assertEquals("\n", matcher.matchOrNull("\n  "))
        assertEquals("\n", matcher.matchOrNull("\n  \t\n"))
        assertEquals("\n\n\n", matcher.matchOrNull("\n\n\n  \t\n"))
    }
}
