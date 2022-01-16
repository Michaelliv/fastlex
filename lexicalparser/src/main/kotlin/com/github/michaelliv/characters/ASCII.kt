package com.github.michaelliv.characters

import com.github.michaelliv.extensions.charSetUnion
import com.github.michaelliv.extensions.openCharSetOf
import com.github.michaelliv.extensions.toCharSet
import com.github.michaelliv.extensions.union
import it.unimi.dsi.fastutil.chars.CharOpenHashSet

object ASCII {
    val symbols = openCharSetOf(
        '!',
        '"',
        '#',
        '$',
        '%',
        '&',
        '\'',
        '(',
        ')',
        '*',
        '+',
        ',',
        '-',
        '.',
        '/',
        ':',
        ';',
        '<',
        '=',
        '>',
        '?',
        '[',
        '\\',
        ']',
        '^',
        '_',
        '`',
        '{',
        '|',
        '}',
        '~',
    )
    val digits: CharOpenHashSet = ('0'..'9').toCharSet()
    val letters: CharOpenHashSet = ('a'..'z') union ('A'..'Z')
    val lettersAndDigits = letters charSetUnion digits
}
