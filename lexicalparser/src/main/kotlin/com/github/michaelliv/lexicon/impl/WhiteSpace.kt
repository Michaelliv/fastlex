package com.github.michaelliv.lexicon.impl

import com.github.michaelliv.characterbuffer.impl.CharacterBuffer
import com.github.michaelliv.lexicon.Lexicon

class WhiteSpace : Lexicon {
    override val name = WHITE_SPACE
    override val overrides = emptyArray<Lexicon>()
    override fun isMatch(cb: CharacterBuffer): Boolean = (cb.advanceCharWhile(Char::isWhitespace) > 0)
}

const val WHITE_SPACE = "WHITE_SPACE"