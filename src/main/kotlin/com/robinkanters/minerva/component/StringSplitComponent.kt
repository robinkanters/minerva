package com.robinkanters.minerva.component

import com.robinkanters.minerva.Flow
import com.robinkanters.minerva.Flow.Companion.flow
import com.robinkanters.minerva.FlowComponent

class StringSplitComponent(vararg val delimiters: String, val joinWith: String = delimiters[0], val subFlow: Flow<List<String>>) : FlowComponent<String> {
    override fun run(payload: String) = payload.split(*delimiters).let { subFlow(it) }.joinToString(joinWith)

    companion object {
        @JvmStatic
        fun Flow<String>.stringSplit(vararg delimiters: String, joinWith: String = delimiters[0], subFlow: Flow<List<String>>.() -> Unit): StringSplitComponent {
            return put(StringSplitComponent(*delimiters, joinWith = joinWith, subFlow = flow("", subFlow)))
        }
    }
}
