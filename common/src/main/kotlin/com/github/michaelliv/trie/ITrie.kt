package com.github.michaelliv.trie

interface INode<K> {
    val childNodes: K
}

interface ITrie<T> {
    val root: T
    fun insert(word: String)
    operator fun contains(char: Char): Boolean
}

