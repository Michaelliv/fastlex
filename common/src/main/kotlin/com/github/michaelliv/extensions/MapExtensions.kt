package com.github.michaelliv.extensions

fun <K, M> MutableMap<K, M>.getOrPut(key: K, valueReceiver: () -> M): M {
    val v = this[key]
    return if (v == null) {
        val value = valueReceiver()
        this[key] = value
        value
    } else v
}
