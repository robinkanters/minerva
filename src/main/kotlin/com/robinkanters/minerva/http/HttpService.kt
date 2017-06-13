package com.robinkanters.minerva.http

interface HttpService {
    fun get(url: String, headers: Map<String, Any>? = emptyMap()): ByteArray
}
