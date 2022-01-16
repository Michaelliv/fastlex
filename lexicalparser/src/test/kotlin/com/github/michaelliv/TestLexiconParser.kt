package com.github.michaelliv

import com.github.michaelliv.lexicalparser.impl.LexicalParser
import com.github.michaelliv.lexicon.BASE_LEXICON
import org.junit.Test
import kotlin.test.assertFails

internal class TestLexiconParser {

    @Test
    fun `markers instance with base markers execute smoothly`() {
//        val baseLexicalParser = LexicalParser(Letter(), Digit(), Symbol(), WhiteSpace())
//        val expectedStrings = listOf("abc", " ", "123", " ", "/.?")
//        val expectedMarks = listOf(
//            Letter.name,
//            WhiteSpace.name,
//            Digit.name,
//            WhiteSpace.name,
//            Symbol.name
//        )
//        val str = "abc 123 /.?"
//        baseLexicalParser.mark(str).forEachIndexed { index, marked ->
//            assertEquals(expectedMarks[index], marked.markedAs)
//            assertEquals(expectedStrings[index], str.slice(marked.range))
//        }
    }

    @Test
    fun `base markers empty string dont break`() {
        val baseLexicalParser = LexicalParser(*BASE_LEXICON)
        val str = ""
        baseLexicalParser.parse(str).forEach { _ -> return }
    }

    @Test
    fun `empty markers throw exception`() {
        assertFails {
            LexicalParser()
        }
    }

    @Test
    fun `markers are sorted correctly`() {
//        val marker = LexicalParser(*BASE_MATCHERS, LikeNumber())
//        assertEquals(marker.sortedOverrides.first().name, LikeNumber.)
    }
}
