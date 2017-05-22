package com.robinkanters.minerva

interface FlowComponent<T> {
    fun run(payload: T): T
}
