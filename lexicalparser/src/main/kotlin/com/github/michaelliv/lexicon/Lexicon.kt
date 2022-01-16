package com.github.michaelliv.lexicon

import com.github.michaelliv.characterbuffer.impl.CharacterBuffer


interface Lexicon {
    val name: String
    val overrides: Array<Lexicon>
    fun isMatch(cb: CharacterBuffer): Boolean
    fun isMatch(string: String): Boolean = isMatch(CharacterBuffer.fromString(string))
    fun matchOrNull(string: String): String? {
        val cb = CharacterBuffer.fromString(string)
        val matched = isMatch(cb)
        return if (matched && cb.hasMoreChars()) string.substring(0 until cb.curIndex)
        else if (matched && cb.noMoreChars()) string.substring(0..cb.curIndex)
        else null
    }
}
