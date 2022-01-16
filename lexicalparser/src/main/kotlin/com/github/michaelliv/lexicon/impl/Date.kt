package com.github.michaelliv.lexicon.impl

import com.github.michaelliv.characterbuffer.impl.CharacterBuffer
import com.github.michaelliv.characters.Unicode
import com.github.michaelliv.extensions.capitalCase
import com.github.michaelliv.extensions.charSetUnion
import com.github.michaelliv.lexicon.BASE_LEXICON
import com.github.michaelliv.lexicon.Lexicon
import com.github.michaelliv.trie.Node
import com.github.michaelliv.trie.impl.Trie
import it.unimi.dsi.fastutil.chars.CharOpenHashSet

const val DATE = "DATE"

private val MONTH_NAMES = listOf(
    "Jan", "January", "Feb", "February", "Mar", "March", "Apr", "April", "May", "May", "Jun", "June", "Jul", "July",
    "Aug", "August", "Sep", "September", "Oct", "October", "Nov", "November", "Dec", "December",
)

private val MONTHS_TRIE = Trie().apply {
    MONTH_NAMES.map(String::lowercase).forEach(this::insert)
    MONTH_NAMES.map(String::uppercase).forEach(this::insert)
    MONTH_NAMES.map(String::capitalCase).forEach(this::insert)
}

private val DEFAULT_TERMINATION_CHARS = Unicode.run {
    WHITE_SPACE charSetUnion FULL_STOP charSetUnion COMMA charSetUnion COLON
}

private val DEFAULT_DATE_SEPARATORS = Unicode.run {
    DASH charSetUnion FULL_STOP charSetUnion COMMA charSetUnion UNDERSCORE charSetUnion SLASH charSetUnion ' '
}

class Date(
    private val dateSeparatorChars: CharOpenHashSet = DEFAULT_DATE_SEPARATORS,
    private val terminationChars: CharOpenHashSet = DEFAULT_TERMINATION_CHARS
) : Lexicon {
    override val name: String = DATE
    override val overrides: Array<Lexicon> = BASE_LEXICON

    override fun isMatch(cb: CharacterBuffer): Boolean {
        val initialIndex = cb.curIndex
        if (markNumericWithSeparator(cb)) return true
        else cb.setPosition(initialIndex)
        return markAlphaNumericDate(cb)
    }

    private fun markNumericWithSeparator(cb: CharacterBuffer): Boolean {
        // Use first number to determine rest of pattern
        val numericCount1 = advanceNumericDate(cb, 4) ?: return false
        // Some kind of invalid date, either 3 char number or year > 9999?
        if (numericCount1 == 0 || cb.curChar !in dateSeparatorChars) return false
        else cb.advanceChar()
        when (numericCount1) {
            4 -> {
                // Case year comes first
                val numericCount2 = advanceNumericDate(cb, 2) ?: return false
                if (numericCount2 !in (1..2) || cb.curChar !in dateSeparatorChars) return false
                cb.advanceCharIf(::isDateSeparator)
                val numericCount3 = advanceNumericDate(cb, 2) ?: return false
                if (numericCount3 !in (1..2)) return false
                return true
            }
            in 1..2 -> {
                // Case month/day comes first
                val numericCount2 = advanceNumericDate(cb, 2) ?: return false
                if (numericCount2 !in (1..2) || cb.curChar !in dateSeparatorChars) return false
                cb.advanceCharIf(::isDateSeparator)
                val numericCount3 = advanceNumericDate(cb, 4) ?: return false
                if (numericCount3 == 0) return false
                return true
            }
            else -> return false
        }
    }

    private fun markAlphaNumericDate(cb: CharacterBuffer): Boolean {
        // Guess which pattern we might be looking at
        if (cb.curChar in MONTHS_TRIE) {
            // Might be a month's name
            // Check <MONTH>
            val monthCount = advanceWhileMonth(cb) ?: return false
            if (monthCount == 0) return false
            // Check <MONTH><SEPARATOR>
            cb.advanceCharWhile(::isDateSeparator)
            // Check <MONTH><SEPARATOR><NUMERIC_DATE>
            val numCount1 = cb.advanceCharWhile(Char::isDigit)
            if (numCount1 == 0) return false
            // <MONTH><NUMERIC_DATE><END_OF_LINE/TERMINATION_CHAR>
            if (numCount1 == 6 && (cb.noMoreChars() || isTermination(cb.curChar)))
                return true
            // Check <MONTH><SEPARATOR><NUMERIC_DATE><SEPARATOR>
            cb.advanceCharWhile(::isDateSeparator)
            // Check <MONTH><SEPARATOR><NUMERIC_DATE><SEPARATOR><NUMERIC_DATE>
            val numCount2 = cb.advanceCharWhile(Char::isDigit)
            if (numCount2 > 0 && (cb.noMoreChars() || isTermination(cb.curChar)))
                return true
            return false
        } else if (cb.curChar.isDigit()) {
            // Might be numeric day or year
            // Check <NUMERIC_DATE>
            val numCount1 = cb.advanceCharWhile(Char::isDigit)
            if (numCount1 == 0 && (cb.noMoreChars() || isTermination(cb.curChar))) return false
            // Check <NUMERIC_DATE><SEPARATOR>
            cb.advanceCharWhile(::isDateSeparator)
            if (cb.noMoreChars() || isTermination(cb.curChar)) return false
            // Check <NUMERIC_DATE><SEPARATOR><ALPHA_MONTH>
            val monthCount = advanceWhileMonth(cb) ?: return false
            if (monthCount == 0) return false
            // Check <NUMERIC_DATE><SEPARATOR><ALPHA_MONTH><SEPARATOR>
            cb.advanceCharWhile(::isDateSeparator)
            if (cb.noMoreChars() || isTermination(cb.curChar)) return false
            // Check <NUMERIC_DATE><SEPARATOR><ALPHA_MONTH><SEPARATOR><NUMERIC_DATE>
            val numCount2 = cb.advanceCharWhile(Char::isDigit)
            if (numCount2 > 0 && (cb.noMoreChars() || isTermination(cb.curChar)))
                return true
            return false
        } else return false
    }

    private fun advanceNumericDate(cb: CharacterBuffer, limit: Int = 0): Int? {
        val digitCount = cb.advanceCharWhile(Char::isDigit)
        if (limit != 0 && digitCount > limit) return null
        return digitCount
    }

    private fun advanceWhileMonth(cb: CharacterBuffer): Int? {
        if (cb.curChar !in MONTHS_TRIE) return null
        var subNode: Node = MONTHS_TRIE.root
        val monthCount = cb.advanceCharWhile {
            if (it !in subNode) return@advanceCharWhile false
            subNode = subNode.childNodes[it]!!
            return@advanceCharWhile true
        }
        if (!subNode.isWord) return null
        if (cb.curChar == '.') cb.advanceChar()
        return monthCount
    }

    private fun isDateSeparator(char: Char) = char in dateSeparatorChars
    private fun isTermination(char: Char) = char in terminationChars
}

