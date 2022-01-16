package com.github.michaelliv.lexicon.impl

import com.github.michaelliv.characterbuffer.impl.CharacterBuffer
import com.github.michaelliv.lexicon.Lexicon

const val DIGIT = "DIGIT"

class Digit : Lexicon {
    override val name = DIGIT
    override val overrides = emptyArray<Lexicon>()
    override fun isMatch(cb: CharacterBuffer): Boolean = cb.advanceCharWhile(Char::isDigit) > 0
}

