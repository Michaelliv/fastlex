package com.github.michaelliv.benchmark

import com.github.michaelliv.characters.ASCII
import com.github.michaelliv.extensions.isSymbol
import java.nio.CharBuffer
import kotlin.random.Random

object StringVSCharBuffer {
    @JvmStatic
    fun main(args: Array<String>) {
        val ascii = ASCII.lettersAndDigits.toList()

        val str: String = TODO()
//        buildString {
//            repeat(500) {
//                ascii.takeRandom(Random.nextInt(5, 10)).forEach(this::append)
//                append(' ')
//            }
//        }

        val charSequence = str as CharSequence
        val charArray = str.toCharArray().toTypedArray()
        val stringBuffer = StringBuffer(str)
        val charBuffer = CharBuffer.wrap(str)

        println("Char iteration:")
        bench("String.forEachIndexed") { str.forEachIndexed { index, char -> } }
        bench("String for i") {
            for (i in 0..str.lastIndex) {
                str[i]
            }
        }
        bench("CharSequence.forEachIndexed") { (str as CharSequence).forEachIndexed { index, char -> } }
        bench("CharSequence for i") {
            val cq = str as CharSequence; for (i in 0..cq.lastIndex) {
            cq[i]
        }
        }
        bench("CharArray.forEachIndexed") { str.toCharArray().toTypedArray().forEachIndexed { index, c -> } }
        bench("CharArray for i") {
            val ca = str.toCharArray().toTypedArray(); for (i in 0..ca.lastIndex) {
            ca[i]
        }
        }
        bench("StringBuffer.forEachIndexed") { StringBuffer(str).forEachIndexed { index, c -> } }
        bench("StringBuffer for i") {
            val sb = StringBuffer(str); for (i in 0..sb.lastIndex) {
            sb[i]
        }
        }
        bench("CharBuffer.forEachIndexed") { CharBuffer.wrap(str).forEachIndexed { index, c -> } }
        bench("CharBuffer for i") {
            val cb = CharBuffer.wrap(str); for (i in 0..cb.lastIndex) {
            cb[i]
        }
        }

        val ranges = buildList {
            repeat(500) {
                val first = Random.nextInt(str.lastIndex)
                val second = Random.nextInt(first, str.lastIndex)
                this.add(first..second)
            }
        }

        println()
        println("Substring:")
        bench("String.substring") { ranges.forEach { str.substring(it) } }
        bench("String.subSequence") { ranges.forEach { str.subSequence(it).toString() } }
        bench("CharSequence.substring") { ranges.forEach { charSequence.substring(it) } }
        bench("CharSequence.subSequence") { ranges.forEach { charSequence.subSequence(it).toString() } }
        bench("CharArray.sliceArray") { ranges.forEach { charArray.sliceArray(it).joinToString("") } }
        bench("CharArray.slice") { ranges.forEach { charArray.slice(it).joinToString("") } }
        bench("StringBuffer.substring") { ranges.forEach { stringBuffer.substring(it) } }
        bench("StringBuffer.substring") { ranges.forEach { stringBuffer.subSequence(it).toString() } }
        bench("CharBuffer.substring") { ranges.forEach { charBuffer.substring(it) } }
        bench("CharBuffer.subSequence") { ranges.forEach { charBuffer.subSequence(it).toString() } }

        println()
        println("Function on char:")
        bench("String.forEachIndexed") { str.forEachIndexed { index, c -> c.isLetterDigitOrSymbol() } }
        bench("String for i") {
            for (i in 0..str.lastIndex) {
                str[i].isLetterDigitOrSymbol()
            }
        }
        bench("CharSequence.forEachIndexed") { (str as CharSequence).forEachIndexed { index, c -> c.isLetterDigitOrSymbol() } }
        bench("CharSequence for i") {
            val cq = str as CharSequence; for (i in 0..cq.lastIndex) {
            cq[i].isLetterDigitOrSymbol()
        }
        }
        bench("CharArray.forEachIndexed") { str.toCharArray().forEachIndexed { index, c -> c.isLetterDigitOrSymbol() } }
        bench("CharArray for i") {
            val ca = str.toCharArray(); for (i in 0..ca.lastIndex) {
            ca[i].isLetterDigitOrSymbol()
        }
        }
        bench("StringBuffer.forEachIndexed") { StringBuffer(str).forEachIndexed { index, c -> c.isLetterDigitOrSymbol() } }
        bench("StringBuffer for i") {
            val sb = StringBuffer(str); for (i in 0..sb.lastIndex) {
            sb[i].isLetterDigitOrSymbol()
        }
        }
        bench("CharBuffer.forEachIndexed") {
            CharBuffer.wrap(str).forEachIndexed { index, c -> c.isLetterDigitOrSymbol() }
        }
        bench("CharBuffer for i") {
            val cb = CharBuffer.wrap(str); for (i in 0..cb.lastIndex) {
            cb[i].isLetterDigitOrSymbol()
        }
        }

    }

    fun Char.isLetterDigitOrSymbol() = isLetter() || isDigit() || isSymbol()
}