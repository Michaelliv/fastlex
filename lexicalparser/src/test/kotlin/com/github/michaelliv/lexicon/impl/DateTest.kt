package com.github.michaelliv.lexicon.impl

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


internal class DateTest {
    @Test
    fun `test numeric dates should match`() {
        val marker = Date()
        // <DAY><SEP><MONTH><SEP><YEAR> or <YEAR><SEP><DAY><SEP><MONTH>
        assertEquals("02/17/2009", marker.matchOrNull("02/17/2009"))
        assertEquals("2/17/2009", marker.matchOrNull("2/17/2009"))
        assertEquals("02/17/2009", marker.matchOrNull("02/17/2009 "))
        assertEquals("02/17/2009", marker.matchOrNull("02/17/2009!"))
        assertEquals("17/02/2009", marker.matchOrNull("17/02/2009"))
        assertEquals("2009/02/17", marker.matchOrNull("2009/02/17"))
        assertEquals("02_17_2009", marker.matchOrNull("02_17_2009"))
        assertEquals("17_02_2009", marker.matchOrNull("17_02_2009"))
        assertEquals("2009_02_17", marker.matchOrNull("2009_02_17"))
        assertEquals("02.17.2009", marker.matchOrNull("02.17.2009"))
        assertEquals("17.02.2009", marker.matchOrNull("17.02.2009"))
        assertEquals("2009.02.17", marker.matchOrNull("2009.02.17"))
        assertEquals("02-17-2009", marker.matchOrNull("02-17-2009"))
        assertEquals("17-02-2009", marker.matchOrNull("17-02-2009"))
        assertEquals("2009-02-17", marker.matchOrNull("2009-02-17"))
        assertEquals("2009 02 17", marker.matchOrNull("2009 02 17"))
        assertEquals("2009 2 17", marker.matchOrNull("2009 2 17"))
    }

    @Test
    fun `test numeric dates should not match`() {
        val marker = Date()
        assertNull(marker.matchOrNull("02/17/"))
        assertNull(marker.matchOrNull("2009-02!-17"))
        assertNull(marker.matchOrNull("2009-02!"))
        assertNull(marker.matchOrNull("2009-02-"))
        assertNull(marker.matchOrNull("2009-02"))
        assertNull(marker.matchOrNull("20091-02-17"))
    }

    @Test
    fun `test alphanumeric with separation should match`() {
        val marker = Date()
        assertEquals("February 17, 2009", marker.matchOrNull("February 17, 2009"))
        assertEquals("17 February, 2009", marker.matchOrNull("17 February, 2009"))
        assertEquals("2009, February 17", marker.matchOrNull("2009, February 17"))
        assertEquals("Feb 17, 2009", marker.matchOrNull("Feb 17, 2009"))
        assertEquals("2009, Feb 17", marker.matchOrNull("2009, Feb 17"))
        assertEquals("Feb 17, 2014", marker.matchOrNull("Feb 17, 2014"))
        assertEquals("17 Feb, 2014", marker.matchOrNull("17 Feb, 2014"))
        assertEquals("2014, Feb 17", marker.matchOrNull("2014, Feb 17"))
        assertEquals("2014_Feb_17", marker.matchOrNull("2014_Feb_17"))
        assertEquals("2014-Feb-17", marker.matchOrNull("2014-Feb-17"))
    }

    @Test
    fun `test alphanumeric with separation should not match`() {
        val marker = Date()
        assertNull(marker.matchOrNull("Ebruary 17, 2009"))
        assertNull(marker.matchOrNull("2014-Feb-abc"))
        assertNull(marker.matchOrNull("February"))
        assertNull(marker.matchOrNull("February, "))
        assertNull(marker.matchOrNull("February,"))
        assertNull(marker.matchOrNull("February!"))
    }

    @Test
    fun `test alphanumeric without separation should match`() {
        val marker = Date()
        // abbreviation with leading zeros
        assertEquals("Feb172009", marker.matchOrNull("Feb172009"))
        assertEquals("17Feb2009", marker.matchOrNull("17Feb2009"))
        assertEquals("2009Feb17", marker.matchOrNull("2009Feb17"))

        // no separators with leading zeros
        // assertEquals("02172009", marker._mark("02172009"))
        // assertEquals("17022009", marker._mark("17022009"))
        // assertEquals("20090217", marker._mark("20090217"))
    }

}
