package com.robinkanters.minerva.http

import org.junit.Assert.assertEquals
import org.junit.Test

class HttpServiceTest {
    @Test fun withoutHeaders() {
        val service = TestHttpService()

        service.get("Foo")

        assertEquals(1, service.capturedRequests.size)
        assertEquals("Foo" to emptyMap<String, Any?>(), service.capturedRequests[0])
    }
}
