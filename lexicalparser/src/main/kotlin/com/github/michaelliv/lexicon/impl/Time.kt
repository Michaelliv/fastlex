package com.github.michaelliv.lexicon.impl

import com.github.michaelliv.characterbuffer.impl.CharacterBuffer
import com.github.michaelliv.characterbuffer.impl.NULL_CHAR
import com.github.michaelliv.extensions.concat
import com.github.michaelliv.extensions.openCharSetOf
import com.github.michaelliv.lexicon.BASE_LEXICON
import com.github.michaelliv.lexicon.Lexicon
import it.unimi.dsi.fastutil.chars.CharOpenHashSet


/**
 * https://docs.oracle.com/cd/E41183_01/DR/Time_Formats.html
 */
class Time(
    private val delimiters: CharOpenHashSet = openCharSetOf(':')
) : Lexicon {
    override val name = TIME
    override val overrides = BASE_LEXICON concat LikeNumber()
    override fun isMatch(cb: CharacterBuffer): Boolean {
        if (!cb.advanceCharIf(Char::isDigit)) return false
        val firstDigit = cb.prevChar
        val secondDigit = if (cb.advanceCharIf(Char::isDigit)) cb.prevChar else NULL_CHAR

        // HH
        if (firstDigit == '2' && secondDigit in '5'..'9') return false
        if (firstDigit in '3'..'4' && secondDigit.isNotNullChar()) return false

        // delimiter
        if (!cb.advanceCharIf(this::isDelimiter)) return false

        // MM
        if (!cb.advanceCharIf { it.isDigit() && it !in ('6'..'9') }) return false
        if (!cb.advanceCharIf(Char::isDigit)) return false

        // This is to trying to avoid clashing with verse numbers (i.e. Matthew 13:54-58)
        // if (cb.curChar == '-') return false
        // Allow HH:MM:SS
        val secondDelimiterIndex = cb.curIndex
        if (cb.advanceCharIf { it in delimiters } && cb.advanceCharWhile(Char::isDigit) == 0) {
            cb.setPosition(secondDelimiterIndex)
            return true
        }

        // Allow HH:MM:SS XM
        val currentIndex = cb.curIndex
        cb.advanceCharIf { it == ' ' }
        if (cb.advanceCharIf { it == 'a' || it == 'A' }) {
            val sepFound = cb.advanceCharIf { it == '.' }
            if (!cb.advanceCharIf { it == 'm' || it == 'M' }) {
                cb.setPosition(currentIndex)
                return true
            }
            if (sepFound) cb.advanceCharIf { it == '.' }
        } else if (cb.advanceCharIf { it == 'p' || it == 'P' }) {
            val sepFound = cb.advanceCharIf { it == '.' }
            if (!cb.advanceCharIf { it == 'm' || it == 'M' }) {
                cb.setPosition(currentIndex)
                return true
            }
            if (sepFound) cb.advanceCharIf { it == '.' }
        } else if (cb.hasMoreChars()) {
            cb.setPosition(currentIndex)
        }

        return true
    }

    private fun isDelimiter(char: Char) = char in delimiters
    private fun Char.isNotNullChar() = this != NULL_CHAR
}

const val TIME = "TIME"