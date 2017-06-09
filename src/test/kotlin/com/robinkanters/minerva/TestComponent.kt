package com.robinkanters.minerva

class TestComponent<T> private constructor(private val result: T) : FlowComponent<T> {
    var calledWith: T? = null
    var timesCalled: Int = 0

    override fun run(payload: T): T {
        calledWith = payload
        timesCalled++

        return result
    }

    companion object {
        fun <T> Flow<T>.test(result: T) = TestComponent(result).apply { this@test += this }
    }
}
