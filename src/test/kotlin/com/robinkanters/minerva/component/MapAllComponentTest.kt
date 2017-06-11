package com.robinkanters.minerva.component

import com.robinkanters.minerva.Flow.Companion.flow
import com.robinkanters.minerva.component.ForEachComponent.Companion.forEach
import com.robinkanters.minerva.component.MapAllComponent.Companion.mapAll
import com.robinkanters.minerva.component.MapComponent.Companion.map
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.system.measureTimeMillis

class MapAllComponentTest {
    @Test
    fun run() {
        val list = (1..3).toList()

        val f = flow<List<Int>>("") {
            mapAll { Thread.sleep(1000); it * 2 }

            forEach {
                map { it * 4 }
                map { it * 8 }
            }
        }

        var result = emptyList<Int>()
        val measured = measureTimeMillis {
            result = f(list)
        }

        assertBetween(measured, 1.second, 2.seconds)
        assertArrayEquals(list.map { it * 64 }.toIntArray(), result.toIntArray())
    }

    private fun assertBetween(actual: Long, min: Int, max: Int) {
        assertTrue(min < max)
        assertTrue(min < actual)
        assertTrue(max > actual)
    }

    val Int.second get() = seconds
    val Int.seconds get() = this * 1000
}
