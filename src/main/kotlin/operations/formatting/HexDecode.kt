package com.nospawnn.operations.formatting

import com.nospawnn.operations.Operation
import kotlin.jvm.Throws

class HexDecode : Operation("From Hex") {
    @Throws(NumberFormatException::class)
    override fun runThis(input: String, opts: Map<String, Any>): String =
        input
            .chunked(3)
            .map { it.trim().toInt(16).toChar() }
            .joinToString("")
}