package com.robinkanters.minerva.component

import com.robinkanters.minerva.Flow
import com.robinkanters.minerva.Flow.Companion.flow
import com.robinkanters.minerva.FlowComponent
import java.util.stream.Stream
import kotlin.streams.toList

class ForEachComponent<T>(val parallel: Boolean = true, val flow: Flow<T>) : FlowComponent<List<T>> {
    override fun run(payload: List<T>) = payload.makeStream().map {
        flow.run(it)
    }.toList()

    fun List<T>.makeStream(): Stream<T> = if (parallel) parallelStream() else stream()

    companion object {
        @JvmStatic
        fun <T> Flow<List<T>>.forEach(parallel: Boolean = true, subflow: Flow<T>.() -> Unit)
                = put(ForEachComponent(parallel, flow("", subflow)))
    }
}
