package com.cdsap.talaiot.request

import com.cdsap.talaiot.logger.LogTracking
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.request.post
import kotlinx.coroutines.experimental.async


import java.net.URL

class Request(
    private val url: String,
    private val content: String,
    logTracker: LogTracking
) {
    init {
        try {
            val client = HttpClient(OkHttp)

            async {

                client.post<String>(URL(url)) {
                    body = content
                    build()

                }
            }
            logTracker.log(url)
        } catch (e: Exception) {
            logTracker.log("HTTPReporting failed: ${e.message} ")
        }
    }
}