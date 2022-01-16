package com.github.michaelliv.lexicon.impl

import com.github.michaelliv.characterbuffer.impl.CharacterBuffer
import com.github.michaelliv.lexicon.Lexicon

class Other : Lexicon {
    override val name: String = OTHER
    override val overrides: Array<Lexicon> = emptyArray()
    override fun isMatch(cb: CharacterBuffer): Boolean = true
}

const val OTHER = "OTHER"