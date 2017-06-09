package com.robinkanters.minerva.component

import com.robinkanters.minerva.Flow
import com.robinkanters.minerva.FlowComponent
import kotlin.streams.toList

class MapAllComponent<T>(val lambda: (T) -> T) : FlowComponent<List<T>> {
    override fun run(payload: List<T>) = payload.parallelStream().map(lambda).toList()

    companion object {
        fun <T> Flow<List<T>>.mapAll(lambda: (T) -> T) = MapAllComponent(lambda).apply { this@mapAll += this }
    }
}