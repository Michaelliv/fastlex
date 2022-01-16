package com.github.michaelliv.overridesTest

import com.github.michaelliv.lexicalparser.impl.LexicalParser


class OverridesTest() {
    companion object {
        fun matchAll(lexicalParser: LexicalParser, input: String): List<IntRange> =
            lexicalParser.parse(input, false).map { it.indexes }.toList()

        @JvmStatic
        fun main(args: Array<String>) {

        }
    }
}