package com.github.michaelliv.trie.impl

import com.github.michaelliv.trie.INode
import com.github.michaelliv.trie.ITrie
import it.unimi.dsi.fastutil.chars.Char2ObjectOpenHashMap


class Node : INode<Char2ObjectOpenHashMap<Node>> {
    var isWord: Boolean = true
    override val childNodes: Char2ObjectOpenHashMap<Node> = Char2ObjectOpenHashMap()
    operator fun contains(char: Char) = childNodes.containsKey(char)
}


class Trie(override val root: Node = Node()) : ITrie<Node> {
    override fun insert(word: String) {
        var currentNode = root
        word.forEach { currentNode = currentNode.childNodes.getOrPut(it) { Node() } }
        currentNode.isWord = true
    }

    override operator fun contains(char: Char) = root.childNodes.contains(char)

    companion object {
        fun from(collection: Collection<String>): Trie {
            return Trie().apply {
                for (str in collection) {
                    insert(str)
                }
            }
        }

        fun from(vararg string: String): Trie {
            return Trie().apply {
                for (str in string) {
                    insert(str)
                }
            }
        }
    }
}