package com.github.michaelliv.hashing

import com.google.common.hash.Hashing

@Suppress("UnstableApiUsage")
val murmurhash3 = Hashing.murmur3_128()
