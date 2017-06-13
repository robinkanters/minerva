package com.robinkanters.minerva.http

import com.github.kittinunf.fuel.httpGet

object FuelService : HttpService {
    override fun get(url: String, headers: Map<String, Any>?) = url.httpGet().header(headers).responseString().second.data
}
