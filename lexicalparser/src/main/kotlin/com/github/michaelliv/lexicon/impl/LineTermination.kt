package com.github.michaelliv.lexicon.impl

import com.github.michaelliv.characterbuffer.impl.CharacterBuffer
import com.github.michaelliv.characters.Unicode
import com.github.michaelliv.lexicon.BASE_LEXICON
import com.github.michaelliv.lexicon.Lexicon

class LineTermination : Lexicon {
    override val name = LINE_TERMINATION
    override val overrides = BASE_LEXICON
    override fun isMatch(cb: CharacterBuffer): Boolean = (cb.advanceCharWhile(::isLineTermination) > 0)
    private fun isLineTermination(char: Char) = char in Unicode.LINE_TERMINATION
}

const val LINE_TERMINATION = "LINE_TERMINATION"