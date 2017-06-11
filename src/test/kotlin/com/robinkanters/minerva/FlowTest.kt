package com.robinkanters.minerva

import com.robinkanters.minerva.Flow.Companion.flow
import com.robinkanters.minerva.TestComponent.Companion.test
import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test
import kotlin.system.measureNanoTime

class FlowTest {
    @Test fun testBasicFlow_ReturnsCorrectResult() {
        val f = flow<String>("Test") {
            test("Bar")
            test("Baz")
        }

        val result = f("Foo")

        assertEquals("Baz", result)
    }

    @Test fun testBasicFlow_HasCorrectAmountOfComponents() {
        val f = flow<String>("Test") {
            test("Bar")
            test("Baz")
        }

        assertEquals(2, f.numComponents)
    }

    @Test fun testBasicFlow_ComponentsAreCalledExactlyOnceWithTheRightParameters() {
        var t1: TestComponent<String>? = null
        var t2: TestComponent<String>? = null

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
        var t1: TestComponent<String>? = null
        var t2: TestComponent<String>? = null
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

    @Ignore @Test fun testPerformanceForFlowWithLotsOfComponents() {
        val numComponents = 100000

        val f = flow<Int>("count flow") {}

        (1..numComponents).forEach { f.test(it) }

        var result = -1
        val timeMeasured = measureNanoTime {
            result = f(0)
        }

        assertEquals(numComponents, f.numComponents)
        assertEquals(numComponents, result)

        println("Running $numComponents components took $timeMeasured nanoseconds (${timeMeasured / 1_000_000_000} seconds)")
        println("That's an average of ${timeMeasured / numComponents} nanoseconds (${(timeMeasured / numComponents.toDouble()) / 1_000_000_000.toDouble()} seconds) per component")
    }
}
