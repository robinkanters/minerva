package com.robinkanters.minerva.component

import com.robinkanters.minerva.Flow.Companion.flow
import com.robinkanters.minerva.component.MapComponent.Companion.map
import org.junit.Assert.assertEquals
import org.junit.Test

class MapComponentTest {
    @Test
    fun testPlusOne() {
        val flow = flow<Int>("") {
            map {
                it + 1
            }
        }

        flow(-1) mustEqual 0
        flow(0) mustEqual 1
        flow(1) mustEqual 2
    }

    @Test
    fun testTimesTwo() {
        val flow = flow<Int>("") {
            map { it * 2 }
        }

        flow(-1) mustEqual -2
        flow(0) mustEqual 0
        flow(1) mustEqual 2
        flow(2) mustEqual 4
    }

    private infix fun <T> T.mustEqual(expected: T) {
        assertEquals(expected, this)
    }
}
