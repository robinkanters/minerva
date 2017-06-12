package com.robinkanters.minerva.component

import com.robinkanters.minerva.Flow
import com.robinkanters.minerva.FlowComponent
import kotlin.streams.toList

class FilterComponent<T>(val lambda: (T) -> Boolean) : FlowComponent<List<T>> {
    override fun run(payload: List<T>) = payload.parallelStream().filter(lambda).toList()

    companion object {
        @JvmStatic
        fun <T> Flow<List<T>>.filter(lambda: (T) -> Boolean) = put(FilterComponent(lambda))
    }
}
