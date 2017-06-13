package com.robinkanters.minerva.http

class TestHttpService : HttpService {
    var response: String? = null

    private val requests = mutableListOf<Pair<String, Map<String, Any>?>>()
    val capturedRequests get() = requests

    override fun get(url: String, headers: Map<String, Any>?): ByteArray {
        requests.add(url to headers)

        return response?.toByteArray() ?: url.toByteArray()
    }
}
