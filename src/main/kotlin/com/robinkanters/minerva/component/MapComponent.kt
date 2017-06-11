package com.robinkanters.minerva.component

import com.robinkanters.minerva.Flow
import com.robinkanters.minerva.FlowComponent

class MapComponent<T>(val lambda: (T) -> T) : FlowComponent<T> {
    override fun run(payload: T): T {
        return lambda(payload)
    }

    companion object {
        fun <T> Flow<T>.map(lambda: (T) -> T) = put(MapComponent(lambda))
    }
}
