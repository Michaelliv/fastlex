package com.github.michaelliv.lexicon

import com.github.michaelliv.lexicon.impl.Digit
import com.github.michaelliv.lexicon.impl.Letter
import com.github.michaelliv.lexicon.impl.Symbol
import com.github.michaelliv.lexicon.impl.WhiteSpace

val BASE_LEXICON = arrayOf(
    Digit(),
    Letter(),
    Symbol(),
    WhiteSpace(),
)