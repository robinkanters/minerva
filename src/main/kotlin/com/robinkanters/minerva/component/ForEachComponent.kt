package com.robinkanters.minerva.component

import com.robinkanters.minerva.Flow
import com.robinkanters.minerva.Flow.Companion.flow
import com.robinkanters.minerva.FlowComponent

class ForEachComponent<T>(val flow: Flow<T>) : FlowComponent<List<T>> {
    override fun run(payload: List<T>) = payload.map { flow.run(it) }

    companion object {
        fun <T> Flow<List<T>>.forEach(subflow: Flow<T>.() -> Unit) = put(ForEachComponent(flow("", subflow)))
    }
}
