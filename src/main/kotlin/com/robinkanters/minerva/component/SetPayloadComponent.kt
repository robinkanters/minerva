package com.robinkanters.minerva.component

import com.robinkanters.minerva.Flow
import com.robinkanters.minerva.FlowComponent

class SetPayloadComponent<T>(val newPayload: T): FlowComponent<T> {
    override fun run(payload: T): T {
        return newPayload
    }

    companion object {
        @JvmStatic
        fun <T> Flow<T>.setPayload(newPayload: T) = put(SetPayloadComponent(newPayload))
    }
}
