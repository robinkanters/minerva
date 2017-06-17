package com.robinkanters.minerva.component

import com.robinkanters.minerva.Flow.Companion.flow
import com.robinkanters.minerva.component.FilterComponent.Companion.filter
import org.junit.Assert.assertEquals
import org.junit.Test

class FilterComponentTest {
    @Test
    fun filterEvenNumbers() {
        val oneToTen = (1..10).toList()
        val evenNumbers = oneToTen.filter { it % 2 == 0 }

        val component = FilterComponent<Int> {
            it % 2 == 0
        }

        assertEquals(evenNumbers, component.run(evenNumbers))
    }

    @Test
    fun filterEvenNumbers_InFlow() {
        val oneToTen = (1..10).toList()
        val evenNumbers = oneToTen.filter { it % 2 == 0 }

        val f = flow<List<Int>> {
            filter { it % 2 == 0 }
        }

        assertEquals(evenNumbers, f(oneToTen))
    }
}
