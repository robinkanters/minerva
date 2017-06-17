package com.robinkanters.minerva.component

import com.robinkanters.minerva.Flow.Companion.flow
import com.robinkanters.minerva.component.MapAllComponent.Companion.mapAll
import com.robinkanters.minerva.component.StringSplitComponent.Companion.stringSplit
import org.junit.Test

import org.junit.Assert.*

class StringSplitComponentTest {
    @Test fun run() {
        val input = "1,2,3,4,5,6,7,8,9,10"

        val flow = flow<String> {
            stringSplit(",") {
                mapAll {
                    (it.toInt() + 10).toString()
                }
            }
        }

        val result = flow(input)

        assertEquals("11,12,13,14,15,16,17,18,19,20", result)
    }
}
