package com.github.michaelliv.lexicon.impl

import com.github.michaelliv.characterbuffer.impl.CharacterBuffer
import com.github.michaelliv.characters.Unicode
import com.github.michaelliv.extensions.charSetUnion
import com.github.michaelliv.lexicon.BASE_LEXICON
import com.github.michaelliv.lexicon.Lexicon
import it.unimi.dsi.fastutil.chars.CharOpenHashSet

val DEFAULT_CONNECTOR_CATEGORIES = hashSetOf(
    CharCategory.MATH_SYMBOL,
    CharCategory.DASH_PUNCTUATION,
    CharCategory.CONNECTOR_PUNCTUATION
)

val DEFAULT_NUMERIC_CATEGORIES = hashSetOf(
    CharCategory.LETTER_NUMBER,
    CharCategory.OTHER_NUMBER,
    CharCategory.DECIMAL_DIGIT_NUMBER
)

val DEFAULT_NUMERIC_PUNCTUATION = Unicode.run {
    FULL_STOP charSetUnion COMMA charSetUnion PERCENT charSetUnion NUMBER_SIGN
}

class LikeNumber(
    private val numericCategories: Set<CharCategory> = DEFAULT_NUMERIC_CATEGORIES,
    private val connectorCategories: Set<CharCategory> = DEFAULT_CONNECTOR_CATEGORIES,
    private val numericPunctuation: CharOpenHashSet = DEFAULT_NUMERIC_PUNCTUATION
) : Lexicon {
    override val name = LIKE_NUMBER
    override val overrides = BASE_LEXICON
    override fun isMatch(cb: CharacterBuffer): Boolean =
        (cb.advanceCharWhile(::isNumeric, ::isPunctuationOrConnector).first > 0)

    private fun isNumeric(char: Char) = char.category in numericCategories
    private fun isPunctuationOrConnector(char: Char) = char.category in connectorCategories || char in numericPunctuation
}

const val LIKE_NUMBER = "LIKE_NUMBER"