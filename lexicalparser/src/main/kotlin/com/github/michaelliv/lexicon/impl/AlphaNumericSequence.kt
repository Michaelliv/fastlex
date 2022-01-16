package com.github.michaelliv.lexicon.impl

import com.github.michaelliv.characterbuffer.impl.CharacterBuffer
import com.github.michaelliv.characters.Unicode
import com.github.michaelliv.extensions.buildCharSet
import com.github.michaelliv.lexicon.BASE_LEXICON
import com.github.michaelliv.lexicon.Lexicon
import it.unimi.dsi.fastutil.chars.CharOpenHashSet

const val ALPHA_NUMERIC_SEQUENCE = "ALPHA_NUMERIC_SEQUENCE"

private val DEFAULT_TERMINATION_CHARS = buildCharSet {
    Unicode.let {
        addAll(it.WHITE_SPACE)
        addAll(it.FULL_STOP)
        addAll(it.COMMA)
        addAll(it.COLON)
        addAll(it.QUESTION_MARK)
        addAll(it.EXCLAMATION_MARK)
    }
}

private val DEFAULT_DASH_PUNCTUATION = buildCharSet {
    Unicode.let {
        addAll(it.DASH)
        addAll(it.UNDERSCORE)
    }
}

class AlphaNumericSequence(
    private val dashPunctuation: CharOpenHashSet = DEFAULT_DASH_PUNCTUATION,
    private val terminationChars: CharOpenHashSet = DEFAULT_TERMINATION_CHARS
) : Lexicon {

    override val name: String = ALPHA_NUMERIC_SEQUENCE
    override val overrides: Array<Lexicon> = BASE_LEXICON

    override fun isMatch(cb: CharacterBuffer): Boolean {
        var charCount = 0

        while (cb.hasMoreChars()) {
            when {
                cb.curChar.isLetter() -> {
                    charCount += 1
                    cb.advanceChar()
                }
                cb.curChar.isDigit() -> cb.advanceChar()
                cb.curChar.isDash() -> cb.advanceChar()
                cb.curChar.isTermination() -> return charCount > 0
                else -> break
            }
        }
        return charCount != 0
    }

    private fun Char.isTermination() = this in terminationChars
    private fun Char.isDash() = this in dashPunctuation
}

