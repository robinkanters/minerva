package com.robinkanters.minerva.component

import com.robinkanters.minerva.Flow.Companion.flow
import com.robinkanters.minerva.component.ForEachComponent.Companion.forEach
import com.robinkanters.minerva.component.MapComponent.Companion.map
import org.junit.Assert.assertArrayEquals
import org.junit.Test

class ForEachComponentTest {
    @Test
    fun run() {
        val list = (1..10).toList()

        val f = flow<List<Int>> {
            forEach {
                map { n -> n * 2 }
            }
        }

        assertArrayEquals(list.map { it * 2 }.toIntArray(), f(list).toIntArray())
    }
}
