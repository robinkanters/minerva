package com.robinkanters.minerva.component

import com.robinkanters.minerva.Flow.Companion.flow
import com.robinkanters.minerva.component.SetPayloadComponent.Companion.setPayload
import org.junit.Assert.assertEquals
import org.junit.Test

class SetPayloadComponentTest {
    @Test fun run() {
        val component = SetPayloadComponent("Foo")

        assertEquals("Foo", component("Bar"))
    }

    @Test fun inFlow() {
        val flow = flow<String> {
            setPayload("Foo")
        }

        val result = flow("Bar")

        assertEquals("Foo", result)
    }
}
