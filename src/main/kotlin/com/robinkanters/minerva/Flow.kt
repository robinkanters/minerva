package com.robinkanters.minerva

class Flow<T> private constructor(@Suppress("unused") val name: String) : FlowComponent<T> {
    private val components = mutableListOf<FlowComponent<T>>()

    val numComponents get() = components.size

    operator fun invoke(payload: T) = run(payload)

    override fun run(payload: T) = process(payload, components)

    private tailrec fun <T> process(payload: T, components: List<FlowComponent<T>>): T {
        if (components.isEmpty()) return payload

        return process(components[0].run(payload), components.drop(1))
    }

    fun put(flowComponent: FlowComponent<T>): Flow<T> {
        components.add(flowComponent)
        return this
    }

    companion object {
        fun <T> flow(name: String, flow: Flow<T>.() -> Unit) = Flow<T>(name).apply { flow(this) }

        fun <T> Flow<T>.flow(flow: Flow<T>.() -> Unit) = put(flow(name, flow))
    }
}
