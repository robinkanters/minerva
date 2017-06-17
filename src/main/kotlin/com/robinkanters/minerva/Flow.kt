package com.robinkanters.minerva

class Flow<T> private constructor(@Suppress("unused") val name: String? = null) : FlowComponent<T> {
    private val components = mutableListOf<FlowComponent<T>>()

    val numComponents get() = components.size

    override fun run(payload: T) = process(payload, components)

    private tailrec fun <T> process(payload: T, components: List<FlowComponent<T>>): T {
        if (components.isEmpty()) return payload

        return process(components[0].run(payload), components.drop(1))
    }

    fun <C: FlowComponent<T>> put(flowComponent: C): C = flowComponent.apply { components.add(this) }

    companion object {
        @JvmStatic
        fun <T> flow(name: String? = null, flow: Flow<T>.() -> Unit) = Flow<T>(name).apply { flow(this) }

        @JvmStatic
        fun <T> Flow<T>.flow(flow: Flow<T>.() -> Unit) = put(flow(name, flow))
    }
}
