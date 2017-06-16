package com.robinkanters.minerva.component

import com.github.kittinunf.fuel.core.requests.write
import com.robinkanters.minerva.Flow
import com.robinkanters.minerva.FlowComponent
import java.io.OutputStream
import java.lang.System.lineSeparator
import java.lang.System.out

class DebugComponent<T>(val outputStream: OutputStream) : FlowComponent<T> {
    override fun run(payload: T) = payload.apply { outputStream.write("${toString()}${lineSeparator()}") }

    companion object {
        @JvmStatic
        fun <T> Flow<T>.debug(outputStream: OutputStream = out) = put(DebugComponent(outputStream))
    }
}
