package com.github.michaelliv

import com.github.michaelliv.extensions.cast
import com.github.michaelliv.extensions.gzipStream
import com.github.michaelliv.extensions.inputStream
import com.github.michaelliv.extensions.toFile
import com.github.michaelliv.lexicalparser.impl.LexicalParser
import com.github.michaelliv.lexicon.BASE_LEXICON
import com.github.michaelliv.lexicon.impl.WORD
import com.github.michaelliv.lexicon.impl.Word
import com.github.michaelliv.lossycounter.impl.StringLossyCounter
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.timer


class WikipediaBenchmark {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val streamCounter = StringLossyCounter(100_000, 10)

            val lineCount = AtomicInteger()
            val lastCount = AtomicInteger()
            val malformedCount = AtomicInteger()

            val parser = LexicalParser(
                Word(), *BASE_LEXICON,
                take = setOf(WORD)
            )

            timer(daemon = true, period = 1000) {
                val lastDone = lastCount.getAndSet(0)
                println("Total: ${lineCount.get()}, [~$lastDone/s]")
            }

            val gson = GsonBuilder()
                .setLenient()
                .create()

            val bufferedReader = "/Users/mlivshits1/Downloads/latest-all.json.gz"
                .inputStream()
                .gzipStream()
                .bufferedReader()

            // Skip first line
            bufferedReader.readLine()

            try {
                bufferedReader
                    .lines()
                    .parallel()
                    .forEach { line ->
                        val lc = lineCount.incrementAndGet()
                        lastCount.incrementAndGet()
                        try {
                            val map = gson.fromJson(line.removeSuffix(","), Map::class.java)

                            val label = map["labels"]
                                .cast<Map<String, Any>>()
                                ?.get("en").cast<Map<String, String>>()
                                ?.get("value")
                                ?: ""

                            val description = map["descriptions"]
                                .cast<Map<String, Any>>()
                                ?.get("en").cast<Map<String, String>>()
                                ?.get("value")
                                ?: ""

                            parser.parse(label)
                                .map { it.textString.lowercase() }
                                .forEach(streamCounter::noStepAdd)

                            parser.parse(description)
                                .map { it.textString.lowercase() }
                                .forEach(streamCounter::noStepAdd)

                            streamCounter.step()

                        } catch (e: JsonSyntaxException) {
                            malformedCount.incrementAndGet()
                        }

                        if (lc >= 4_000_000) throw NoSuchElementException()
                    }
            } catch (e: NoSuchElementException) {

            }

            "/Users/mlivshits1/Projects/fastlex/wikidata/results.tsv".toFile().bufferedWriter().use { writer ->
                streamCounter.complete().let {
                    it.object2LongEntrySet().sortedByDescending { it.longValue }.forEach { (str, count) ->
                        writer.write(str)
                        writer.write("\t")
                        writer.write(count.toString())
                        writer.write("\n")
                    }
                }
            }

        }
    }

}