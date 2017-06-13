package com.robinkanters.minerva

interface FlowComponent<T> {
    fun run(payload: T): T

    operator fun invoke(payload: T) = run(payload)
}
