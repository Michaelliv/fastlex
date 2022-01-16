package com.github.michaelliv.index

import org.apache.lucene.document.Document
import org.apache.lucene.index.IndexWriter
import org.apache.lucene.index.IndexWriterConfig
import org.apache.lucene.store.Directory
import java.util.concurrent.ArrayBlockingQueue
import kotlin.concurrent.thread
import kotlin.concurrent.timer

class QueueWriter(
    directory: Directory,
    config: IndexWriterConfig,
    private val threadCount: Int,
    private val queueCapacity: Int
) : IndexWriter(directory, config) {
    init {
        require(threadCount > 0)
        require(queueCapacity > 0)
    }

    private val writeQueue = ArrayBlockingQueue<Any>(queueCapacity)
    private val writersPool = ArrayList<Thread>(0)

    init {
        timer(daemon = true, period = 1000L) {
            if (writeQueue.size > 0 && writersPool.size == 0) writersPool.add(createWriterThread())
            val moreThreadsAvailable = writersPool.size < threadCount
            val queueAboveThreshold = writeQueue.size.toFloat() / queueCapacity.toFloat() > 0.3
            if (moreThreadsAvailable && queueAboveThreshold) writersPool.add(createWriterThread())
//            val
        }
    }

    fun enqueue(doc: Document) = writeQueue.add(doc)
    fun complete() {
        writeQueue.add(POISON_PILL)
        writersPool.forEach { it.join() }
        this.close()
    }

    private fun createWriterThread(): Thread {
        return thread {
            val nexts = ArrayList<Any>()
            while (!Thread.currentThread().isInterrupted) {
                nexts.clear()
                nexts.add(writeQueue.take())
                writeQueue.drainTo(nexts)
                for (next in nexts) {
                    if (next === POISON_PILL) {
                        writeQueue.add(POISON_PILL)
                        return@thread
                    }
                    next as Document
                    this.addDocument(next)
                }
            }
        }

    }

    companion object {
        val POISON_PILL = Any()
    }
}


