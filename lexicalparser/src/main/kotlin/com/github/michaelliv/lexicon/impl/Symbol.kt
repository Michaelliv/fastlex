package com.github.michaelliv.lexicon.impl

import com.github.michaelliv.characterbuffer.impl.CharacterBuffer
import com.github.michaelliv.extensions.isSymbol
import com.github.michaelliv.lexicon.Lexicon

class Symbol : Lexicon {
    override val name = SYMBOL
    override val overrides = emptyArray<Lexicon>()
    override fun isMatch(cb: CharacterBuffer): Boolean = cb.advanceCharIf(Char::isSymbol)
}

const val SYMBOL = "SYMBOL"