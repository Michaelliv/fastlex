package com.github.michaelliv.lexicon.impl

import com.github.michaelliv.characterbuffer.impl.CharacterBuffer
import com.github.michaelliv.characters.ASCII
import com.github.michaelliv.extensions.toCharSet
import com.github.michaelliv.lexicon.BASE_LEXICON
import com.github.michaelliv.lexicon.Lexicon
import it.unimi.dsi.fastutil.chars.CharOpenHashSet


private val DEFAULT_LOCAL_SYMBOLS = "_-.+".toCharSet()
private val DEFAULT_LOCAL_CHARS = ASCII.lettersAndDigits
private val DEFAULT_DOMAIN_SYMBOLS = "-".toCharSet()
private val DEFAULT_DOMAIN_CHARS = ASCII.lettersAndDigits
private val DEFAULT_TLD_SYMBOLS = "-.".toCharSet()
private val DEFAULT_TLD_CHARS = ASCII.lettersAndDigits

/**
 * Inspired by the simple email rules in:
 * https://github.com/explosion/spaCy/blob/2bf52c44b1cecc62204bf9366a559bcef305b8ee/spacy/lang/lex_attrs.py
 *
 * Local part:
 * lowercase Latin letters: abcdefghijklmnopqrstuvwxyz
 * uppercase Latin letters: ABCDEFGHIJKLMNOPQRSTUVWXYZ
 * digits: 0123456789
 * symbols: _-.+
 *
 * Domain part:
 * lowercase Latin letters: abcdefghijklmnopqrstuvwxyz
 * uppercase Latin letters: ABCDEFGHIJKLMNOPQRSTUVWXYZ
 * digits: 0123456789
 * symbols: -
 *
 * TLD:
 * lowercase Latin letters: abcdefghijklmnopqrstuvwxyz
 * uppercase Latin letters: ABCDEFGHIJKLMNOPQRSTUVWXYZ
 * digits: 0123456789,
 * symbols: -.
 */
class LikeEmail(
    private val localSymbols: CharOpenHashSet = DEFAULT_LOCAL_SYMBOLS,
    private val localChars: CharOpenHashSet = DEFAULT_LOCAL_CHARS,
    private val domainSymbols: CharOpenHashSet = DEFAULT_DOMAIN_SYMBOLS,
    private val domainChars: CharOpenHashSet = DEFAULT_DOMAIN_CHARS,
    private val tldSymbols: CharOpenHashSet = DEFAULT_TLD_SYMBOLS,
    private val tldChars: CharOpenHashSet = DEFAULT_TLD_CHARS,
) : Lexicon {
    override val name = LIKE_EMAIL
    override val overrides = BASE_LEXICON

    override fun isMatch(cb: CharacterBuffer): Boolean {
        val localLen = cb.advanceCharWhile(this::isLocalSymbol, this::isLocalChar)
        if (localLen.second == 0) return false
        val isAtChar = cb.advanceCharIf { it == '@' }
        if (!isAtChar) return false
        val domainLen = cb.advanceCharWhile(this::isDomainSymbol, this::isDomainChar)
        if (domainLen.second == 0) return false
        val isDotChar = cb.advanceCharIf { it == '.' }
        if (!isDotChar) return false
        val tldLen = cb.advanceCharWhile(this::isTLDSymbol, this::isTLDChar)
        if (tldLen.second == 0) return false
        return true
    }

    private fun isLocalSymbol(char: Char) = localSymbols.contains(char)
    private fun isLocalChar(char: Char) = localChars.contains(char)
    private fun isDomainSymbol(char: Char) = domainSymbols.contains(char)
    private fun isDomainChar(char: Char) = domainChars.contains(char)
    private fun isTLDSymbol(char: Char) = tldSymbols.contains(char)
    private fun isTLDChar(char: Char) = tldChars.contains(char)

}

const val LIKE_EMAIL = "LIKE_EMAIL"