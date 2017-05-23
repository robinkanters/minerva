package com.robinkanters.minerva

import com.robinkanters.minerva.Flow.Companion.flow
import com.robinkanters.minerva.TestComponent.Companion.test
import org.junit.Assert.assertEquals
import org.junit.Test

class FlowTest {
    @Test fun testBasicFlow_ReturnsCorrectResult() {
        val f = flow<String>("Test") {
            test("Bar")
            test("Baz")
        }

        val result = f("Foo")

        assertEquals("Baz", result)
    }

    @Test fun testBasicFlow_ComponentsAreCalledExactlyOnceWithTheRightParameters() {
        var t1: TestComponent? = null
        var t2: TestComponent? = null

        val f = flow<String>("Test") {
            t1 = test("Bar")
            t2 = test("Baz")
        }

        f("Foo")

        assertEquals("Foo", t1!!.calledWith)
        assertEquals(1, t1!!.timesCalled)
        assertEquals("Bar", t2!!.calledWith)
        assertEquals(1, t2!!.timesCalled)
    }

    @Test fun testFlowWithSubflow_ComponentsAreCalledExactlyOnceWithTheRightParameters() {
        var t1: TestComponent? = null
        var t2: TestComponent? = null
        var subFlow: Flow<String>? = null

        val f = flow<String>("Test") {
            t1 = test("Bar")

            subFlow = flow {
                t2 = test("Baz")
            }
        }

        val result = f("Foo")

        assertEquals("Baz", result)
        assertEquals("Foo", t1!!.calledWith)
        assertEquals(1, t1!!.timesCalled)
        assertEquals("Bar", t2!!.calledWith)
        assertEquals(1, t2!!.timesCalled)

        assertEquals("Baz", subFlow!!(""))
    }
}
