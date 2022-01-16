package com.github.michaelliv.lexicon.impl

import com.github.michaelliv.characterbuffer.impl.CharacterBuffer
import com.github.michaelliv.characterbuffer.impl.advanceCharWhileInTrie
import com.github.michaelliv.extensions.charSetUnion
import com.github.michaelliv.extensions.openCharSetOf
import com.github.michaelliv.lexicon.BASE_LEXICON
import com.github.michaelliv.lexicon.Lexicon
import com.github.michaelliv.trie.impl.Trie
import it.unimi.dsi.fastutil.chars.CharOpenHashSet

private val SCHEMES_TRIE = Trie.from(
    "http://", "https://", "www."
)

private val DEFAULT_RESERVED_SYMBOLS = openCharSetOf(
    '!', '$', '&', '\'', '(', ')', '*', '+', ',', ';', '=', ':', '/', '?', '#', '[', ']', '@'
)

private val DEFAULT_UNRESERVED_SYMBOLS = openCharSetOf(
    '-', '.', '_', '~'
)

/**
 * https://www.rfc-editor.org/rfc/rfc3986
 */
class LikeURL(
    reservedSymbols: CharOpenHashSet = DEFAULT_RESERVED_SYMBOLS,
    unreservedSymbols: CharOpenHashSet = DEFAULT_UNRESERVED_SYMBOLS
) : Lexicon {
    override val name = LIKE_URL
    override val overrides = BASE_LEXICON
    private val allUrlSymbols = reservedSymbols charSetUnion unreservedSymbols

    override fun isMatch(cb: CharacterBuffer): Boolean {
        if (isDot(cb.curChar)) return false
        // Detected either scheme or www
        val schemeAdvance = cb.advanceCharWhileInTrie(SCHEMES_TRIE)
        if (schemeAdvance.isWord) {
            cb.advanceCharWhile(Char::isLetterOrDigit, this::isDot, this::inUrlSymbols)
            return true
        }
        val urlChars = cb.advanceCharWhile { it in allUrlSymbols }
        if (urlChars == 0) return false

        return true
    }

    private fun isDot(char: Char) = char == '.'
    private fun inUrlSymbols(char: Char) = char in allUrlSymbols
}

const val LIKE_URL = "LIKE_URL"