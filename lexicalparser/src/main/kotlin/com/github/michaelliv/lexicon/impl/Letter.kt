package com.github.michaelliv.lexicon.impl

import com.github.michaelliv.characterbuffer.impl.CharacterBuffer
import com.github.michaelliv.lexicon.Lexicon

class Letter : Lexicon {
    override val name = LETTER
    override val overrides = emptyArray<Lexicon>()
    override fun isMatch(cb: CharacterBuffer): Boolean = (cb.advanceCharWhile(Char::isLetter) > 0)
}

const val LETTER = "LETTER"