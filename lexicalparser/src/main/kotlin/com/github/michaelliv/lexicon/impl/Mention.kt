package com.github.michaelliv.lexicon.impl

import com.github.michaelliv.characterbuffer.impl.CharacterBuffer
import com.github.michaelliv.extensions.concat
import com.github.michaelliv.lexicon.BASE_LEXICON
import com.github.michaelliv.lexicon.Lexicon

class Mention : Lexicon {
    override val name = MENTION
    override val overrides = BASE_LEXICON concat LikeNumber()
    override fun isMatch(cb: CharacterBuffer): Boolean {
        if (cb.curChar != '@') return false
        else cb.advanceChar()
        val charCount = cb.advanceCharWhile(this::isUnderScore, Char::isLetterOrDigit)
        if (charCount.second == 0) return false
        return true
    }

    private fun isUnderScore(char: Char) = char == '_'
}

const val MENTION = "MENTION"