package com.robinkanters.minerva.component

import com.robinkanters.minerva.Flow.Companion.flow
import com.robinkanters.minerva.component.DebugComponent.Companion.debug
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test
import java.io.ByteArrayOutputStream

class DebugComponentTest {
    private val stream = ByteArrayOutputStream()
    private val streamContents: String
        get() = String(stream.toByteArray())

    @Test
    fun run() {
        val component = DebugComponent<String>(stream)

        component("Foo")

        assertEquals("Foo\n", streamContents)
    }

    @Test fun inFlow() {
        val flow = flow<String>("") {
            debug(stream)
        }

        val result = flow("Foo")

        assertEquals("Foo", result)
        assertEquals("Foo\n", streamContents)
    }

    @Test fun withDataClass() {
        val person = Person("Jeremy", 45)

        val component = DebugComponent<Person>(stream)

        val result = component(person)

        assertSame(person, result)
        assertEquals("$person\n", streamContents)
    }

    data class Person(val name: String, val age: Int)
}
