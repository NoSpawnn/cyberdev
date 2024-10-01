package com.nospawnn.operations.formatting

import com.nospawnn.operations.Operation
import kotlin.io.encoding.ExperimentalEncodingApi

class HexEncode : Operation("To Hex") {
    @OptIn(ExperimentalEncodingApi::class)
    override fun runThis(input: String, opts: Map<String, Any>): String =
        input
            .toByteArray()
            .joinToString(" ") { String.format("%02x", it) }

}