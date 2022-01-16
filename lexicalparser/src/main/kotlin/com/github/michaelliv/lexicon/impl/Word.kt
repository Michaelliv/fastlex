package com.github.michaelliv.lexicon.impl

import com.github.michaelliv.characterbuffer.impl.CharacterBuffer
import com.github.michaelliv.characters.Unicode
import com.github.michaelliv.extensions.buildCharSet
import com.github.michaelliv.lexicon.BASE_LEXICON
import com.github.michaelliv.lexicon.Lexicon
import it.unimi.dsi.fastutil.chars.CharOpenHashSet

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

class Word(private val terminationChars: CharOpenHashSet = DEFAULT_TERMINATION_CHARS) : Lexicon {

    override val name: String = WORD
    override val overrides: Array<Lexicon> = BASE_LEXICON

    override fun isMatch(cb: CharacterBuffer): Boolean {
        var charCount = 0
        var apostropheIndex = -1

        while (cb.hasMoreChars()) {
            when {
                cb.curChar.isLetter() -> {
                    charCount += 1
                    cb.advanceChar()
                }
                cb.curChar.isApostrophe() -> {
                    if (apostropheIndex != -1) return false
                    apostropheIndex = cb.curIndex
                    cb.advanceChar()
                }
                cb.curChar.isDash() -> cb.advanceChar()
                cb.curChar.isTermination() -> return charCount > 0
                else -> return false
            }
        }
        return charCount != 0
    }

    private fun Char.isTermination() = this in terminationChars
    private fun Char.isApostrophe() = this in Unicode.APOSTROPHE
    private fun Char.isDash() = this in Unicode.DASH
}

const val WORD = "WORD"