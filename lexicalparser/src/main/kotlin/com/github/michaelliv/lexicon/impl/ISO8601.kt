package com.github.michaelliv.lexicon.impl

import com.github.michaelliv.characterbuffer.impl.CharacterBuffer
import com.github.michaelliv.lexicon.BASE_LEXICON
import com.github.michaelliv.lexicon.Lexicon

const val DATE_ISO8601 = "DATE_ISO_8601"

class ISO8601 : Lexicon {
    override val name: String = DATE_ISO8601
    override val overrides: Array<Lexicon> = BASE_LEXICON

    /**
     * Year:
     * YYYY (eg 1997)
     * Year and month:
     * YYYY-MM (eg 1997-07)
     * Complete date:
     * YYYY-MM-DD (eg 1997-07-16)
     * Complete date plus hours and minutes:
     * YYYY-MM-DDThh:mmTZD (eg 1997-07-16T19:20+01:00)
     * Complete date plus hours, minutes and seconds:
     * YYYY-MM-DDThh:mm:ssTZD (eg 1997-07-16T19:20:30+01:00)
     * Complete date plus hours, minutes, seconds and a decimal fraction of a
     * second
     * YYYY-MM-DDThh:mm:ss.sTZD (eg 1997-07-16T19:20:30.45+01:00)
     * where:
     *
     * YYYY = four-digit year
     * MM   = two-digit month (01=January, etc.)
     * DD   = two-digit day of month (01 through 31)
     * hh   = two digits of hour (00 through 23) (am/pm NOT allowed)
     * mm   = two digits of minute (00 through 59)
     * ss   = two digits of second (00 through 59)
     * s    = one or more digits representing a decimal fraction of a second
     * TZD  = time zone designator (Z or +hh:mm or -hh:mm)
     */
    override fun isMatch(cb: CharacterBuffer): Boolean {
        // YYYY
        repeat(4) { if (!cb.advanceCharIf(Char::isDigit)) return false }
        // -
        if (!cb.advanceCharIf { it == '-' }) return false
        // MM
        repeat(2) { if (!cb.advanceCharIf(Char::isDigit)) return false }
        // -
        if (!cb.advanceCharIf { it == '-' }) return false
        // DD
        repeat(2) { if (!cb.advanceCharIf(Char::isDigit)) return false }
        val yymmhhIndex = cb.curIndex
        // T
        if (!cb.advanceCharIf { it == 'T' || it == ' ' }) return true
        // hh
        repeat(2) {
            if (!cb.advanceCharIf(Char::isDigit)) {
                cb.setPosition(yymmhhIndex)
                return true
            }
        }
        val hhIndex = cb.curIndex
        // :
        if (!cb.advanceCharIf { it == ':' }) return true
        // mm
        repeat(2) {
            if (!cb.advanceCharIf(Char::isDigit)) {
                cb.setPosition(hhIndex)
                return true
            }
        }
        val mmIndex = cb.curIndex
        // :
        if (!cb.advanceCharIf { it == ':' }) return true
        // ss
        repeat(2) {
            if (!cb.advanceCharIf(Char::isDigit)) {
                cb.setPosition(mmIndex)
                return true
            }
        }
        val ssIndex = cb.curIndex
        // .s
        if (cb.advanceCharIf { it == '.' } && cb.advanceCharWhile(Char::isDigit) == 0) {
            cb.setPosition(ssIndex)
            return true
        }
        val sIndex = cb.curIndex
        // TZD - Case Z
        if (cb.advanceCharIf { it == 'Z' }) return true
        // TZD - Case TZ
        if (cb.advanceCharIf { it == '+' || it == '-' }) {
            // hh
            repeat(2) {
                if (!cb.advanceCharIf(Char::isDigit)) {
                    cb.setPosition(sIndex)
                    return true
                }
            }
            val thhIndex = cb.curIndex
            // :
            if (!cb.advanceCharIf { it == ':' }) return true
            // mm
            repeat(2) {
                if (!cb.advanceCharIf(Char::isDigit)) {
                    cb.setPosition(thhIndex)
                    return true
                }
            }
            return true
        }
        return true
    }
}

