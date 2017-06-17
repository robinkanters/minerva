package com.robinkanters.minerva.component

import com.github.kittinunf.fuel.Fuel
import com.robinkanters.minerva.Flow.Companion.flow
import com.robinkanters.minerva.component.HttpGetComponent.Companion.httpGet
import com.robinkanters.minerva.http.TestHttpService
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HttpGetComponentTest {
    val url = "https://random.show/podcasts/hello-internet/episodes/h-i--30--fibonacci-dog-years.json"

    lateinit var testHttpService: TestHttpService

    @Before
    fun setUp() {
        Fuel.testMode()
        testHttpService = TestHttpService()
    }

    @Test fun run() {
        val component = HttpGetComponent(url, httpService = testHttpService)
        testHttpService.response = "Bar"

        val response = component("Foo")

        assertEquals("Bar", response)
        assertEquals(1, testHttpService.capturedRequests.size)
        assertEquals(url to emptyMap<String, Any?>(), testHttpService.capturedRequests[0])
    }

    @Test fun run_WithoutPredefinedUrl_UsesPayloadAsUrl() {
        val component = HttpGetComponent(httpService = testHttpService)
        testHttpService.response = "Bar"

        val response = component.run("Foo")

        assertEquals("Bar", response)
        assertEquals("Foo" to emptyMap<String, Any?>(), testHttpService.capturedRequests[0])
    }

    @Test fun httpGetComponentInFlow() {
        testHttpService.response = "Bar"

        val flow = flow<String>("") {
            httpGet("Foo", httpService = testHttpService)
        }

        val result = flow("Foo")

        assertEquals("Bar", result)
        assertEquals("Foo" to emptyMap<String, Any?>(), testHttpService.capturedRequests[0])
    }

    @Test fun run_WithActualCall() {
        val component = HttpGetComponent()

        val response = component.run(url)

        (0..2).endInclusive

        assertEquals("{\"", response.substring(0..1))
    }
}
