package com.robinkanters.minerva

class Flow<T> private constructor(@Suppress("unused") val name: String) : FlowComponent<T> {
    private val components: MutableList<FlowComponent<T>> = mutableListOf()

    operator fun invoke(payload: T) = run(payload)

    override fun run(payload: T) = process(payload, components)

    private tailrec fun <T> process(payload: T, components: List<FlowComponent<T>>): T {
        if (components.isEmpty()) return payload

        return process(components[0].run(payload), components.drop(1))
    }

    operator fun plusAssign(flowComponent: FlowComponent<T>): Unit {
        components.add(flowComponent)
    }

    companion object {
        fun <T> flow(name: String, flow: Flow<T>.() -> Unit) = Flow<T>(name).apply { flow(this) }

        fun <T> Flow<T>.flow(flow: Flow<T>.() -> Unit) = this@Companion.flow(name, flow).apply { this@flow += this }
    }
}
