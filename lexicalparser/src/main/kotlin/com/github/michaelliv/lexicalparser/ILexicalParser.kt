package com.github.michaelliv.lexicalparser


class Parsed(
    val lexicon: String,
    val indexes: IntRange,
    var text: CharSequence = ""
) {
    val textString get() = (text as String)
    override fun toString() = "$lexicon [${indexes.first}, ${indexes.last}] $text"
}

interface ILexicalParser {
    fun parse(string: String, withString: Boolean = true): Sequence<Parsed>
}

