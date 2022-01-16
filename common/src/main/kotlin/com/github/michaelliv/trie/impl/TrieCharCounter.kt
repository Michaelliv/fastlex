package com.github.michaelliv.trie.impl

import com.github.michaelliv.trie.INode
import com.github.michaelliv.trie.ITrie
import it.unimi.dsi.fastutil.chars.Char2ObjectOpenHashMap

class NodeCharCounter: INode<Char2ObjectOpenHashMap<NodeCharCounter>> {
    var isWord: Boolean = false
    var charOccurrence: Int = 0
    override val childNodes: Char2ObjectOpenHashMap<NodeCharCounter> = Char2ObjectOpenHashMap()
    operator fun contains(char: Char) = childNodes.containsKey(char)
}

class TrieCharCounter(override val root: NodeCharCounter = NodeCharCounter()) : ITrie<NodeCharCounter> {
    override fun insert(word: String) {
        var currentNode = root
        word.forEach {
            currentNode = currentNode.childNodes.getOrPut(it) { NodeCharCounter() }
            currentNode.charOccurrence++
        }
        currentNode.isWord = true
    }

    fun traverseTop(prefix: String, topN: Int): List<String> {
        var currentNode = root
        for (char in prefix) {
            if (!currentNode.childNodes.containsKey(char)) return emptyList()
            currentNode = currentNode.childNodes.get(char)
        }
        val topNodes = currentNode.childNodes.char2ObjectEntrySet().sortedByDescending { it.value.charOccurrence }.take(topN)
        val topNodesTraversed = topNodes.associate { it.charKey to arrayListOf<Char>() }
        topNodes.forEach { traverseMostFrequent(it.value, topNodesTraversed[it.charKey]!!) }
        return topNodesTraversed.map {
            buildString {
                append(prefix)
                append(it.key)
                it.value.forEach { c -> append(c) }
            }
        }
    }

    private fun traverseMostFrequent(node: NodeCharCounter, chars: ArrayList<Char>) {
        val topNode = node.childNodes.char2ObjectEntrySet().maxByOrNull { it.value.charOccurrence }!!
        chars.add(topNode.charKey)
        if (!topNode.value.isWord) traverseMostFrequent(topNode.value, chars)
    }


    override operator fun contains(char: Char) = root.childNodes.contains(char)

//    companion object {
//        @JvmStatic
//        fun main(args: Array<String>) {
//            val gson = Gson()
//            val trie = TrieCharCounter()
//            var exceptionCount = 0
//            var done = 0
//
//            val t = timer(daemon = true, period = 1000) {
//                println("Done: $done, exception: $exceptionCount")
//            }
//
//            val inputStream ="/Users/mlivshits1/Projects/data/20211219105835/companies.jsonl".toFile().bufferedReader()
//            inputStream.useLines {
//                it.forEach { line ->
//                    val map = gson.fromJson(line, Map::class.java)
//                    val names = map["company_name_cleaned"].cast<ArrayList<String>>()
//                    try {
//                        names
//                            ?.filter { fullName -> fullName.length > 3 }
//                            ?.forEach { fullName -> trie.insert(fullName) }
//                    } catch (e: Exception) {
//                        exceptionCount++
//                    }
//                    done++
//                }
//            }
//            t.cancel()
//            while (true) {
//                println("Prefix:")
//                val line = readLine()
//                val topWords = trie.traverseTop(line!!, 10)
//                println(topWords)
//            }
//        }
//    }
}