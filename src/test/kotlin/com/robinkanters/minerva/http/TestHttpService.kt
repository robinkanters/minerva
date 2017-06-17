package com.robinkanters.minerva.http

class TestHttpService : HttpService {
    var response: String? = null

    var capturedRequests = arrayOf<Pair<String, Map<String, Any>?>>()
        private set

    override fun get(url: String, headers: Map<String, Any>?): ByteArray {
        capturedRequests += url to headers

        return response?.toByteArray() ?: url.toByteArray()
    }
}
