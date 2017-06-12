package com.robinkanters.minerva.component

import com.robinkanters.minerva.Flow
import com.robinkanters.minerva.FlowComponent
import kotlin.streams.toList

class MapAllComponent<T>(val lambda: (T) -> T) : FlowComponent<List<T>> {
    override fun run(payload: List<T>) = payload.parallelStream().map(lambda).toList()

    companion object {
        @JvmStatic
        fun <T> Flow<List<T>>.mapAll(lambda: (T) -> T) = put(MapAllComponent(lambda))
    }
}
