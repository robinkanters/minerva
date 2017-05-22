package com.robinkanters.minerva

class TestComponent private constructor(private val result: String) : FlowComponent<String> {
    var calledWith: String? = null
    var timesCalled: Int = 0

    override fun run(payload: String): String {
        calledWith = payload
        timesCalled++

        return result
    }

    companion object {
        fun Flow<String>.test(result: String) = TestComponent(result).apply { this@test += this }
    }
}
