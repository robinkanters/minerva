package com.robinkanters.minerva.component

import com.robinkanters.minerva.Flow
import com.robinkanters.minerva.FlowComponent
import com.robinkanters.minerva.http.FuelService
import com.robinkanters.minerva.http.HttpService

class HttpGetComponent(val url: String? = null, val headers: Map<String, String> = emptyMap(), val httpService: HttpService = FuelService) : FlowComponent<String> {
    override fun run(payload: String): String {
        val requestUrl = determineRequestUrl(payload)

        val response = httpService.get(requestUrl, headers)

        return String(response)
    }

    fun determineRequestUrl(payload: String) = url ?: payload

    companion object {
        @JvmStatic
        fun Flow<String>.httpGet(url: String? = null, headers: Map<String, String> = emptyMap(), httpService: HttpService = FuelService)
                = put(HttpGetComponent(url, headers, httpService))
    }
}
