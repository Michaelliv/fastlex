package com.github.michaelliv.lexicalparser.impl

import com.github.michaelliv.characterbuffer.impl.CharacterBuffer
import com.github.michaelliv.lexicalparser.ILexicalParser
import com.github.michaelliv.lexicalparser.Parsed
import com.github.michaelliv.lexicon.Lexicon


class LexicalParser(
    vararg lexicon: Lexicon,
    private val take: Set<String> = emptySet()
) : ILexicalParser {
    init {
        if (lexicon.isEmpty()) throw IllegalStateException("Cannot initialized LexiconParser instance without parsers")
    }

    val sortedLexicon: List<Lexicon> = lexicon.sortTopologically()

    override fun parse(string: String, withString: Boolean): Sequence<Parsed> {
        if (string.isEmpty()) return emptySequence()
        return sequence {
            val cb = CharacterBuffer.fromString(string)
            var currentIndex = 0
            while (cb.hasMoreChars()) {
                for (matcher in sortedLexicon) {
                    if (matcher.isMatch(cb)) {
                        if (take.isNotEmpty() && matcher.name !in take) {
                            currentIndex = cb.curIndex
                            break
                        }

                        val range = if (cb.hasMoreChars()) currentIndex until cb.curIndex else currentIndex..cb.curIndex
                        val parsed = if (withString) Parsed(matcher.name, range, string.subSequence(range))
                        else Parsed(matcher.name, range)
                        yield(parsed)
                        currentIndex = cb.curIndex
                        break
                    }
                    if (currentIndex != cb.curIndex)
                        cb.setPosition(currentIndex)
                }
            }
        }
    }

    fun Array<out Lexicon>.sortTopologically(): List<Lexicon> {
        val overridesGraph: Map<String, Lexicon?> = this.associateBy { it.name }
        val result: MutableList<String> = mutableListOf()
        val seen: MutableSet<String> = hashSetOf()

        fun recursiveHelper(markerName: String) {
            val beforeMarker = overridesGraph.getOrDefault(markerName, null)
            beforeMarker?.overrides?.forEach {
                if (!seen.contains(it.name)) {
                    seen.add(it.name)
                    recursiveHelper(it.name)
                }
            }
            if (!result.contains(markerName)) result.add(markerName)
        }

        overridesGraph.keys.forEach { recursiveHelper(it) }
        return result.filter { it in overridesGraph }.map { overridesGraph[it]!! }.reversed()
    }
}

fun Sequence<Parsed>.take(vararg name: String) =
    filter { it.lexicon in name.toHashSet() }

fun Sequence<Parsed>.map(name: String, block: (String) -> String) =
    map { if (it.lexicon == name && it.text.isNotEmpty()) Parsed(it.lexicon, it.indexes, block(it.text.toString())) else it }
