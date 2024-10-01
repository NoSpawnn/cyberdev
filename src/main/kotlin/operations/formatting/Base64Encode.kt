package com.nospawnn.operations.formatting

import com.nospawnn.operations.Operation
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class Base64Encode : Operation("To Base64") {
    @OptIn(ExperimentalEncodingApi::class)
    override fun runThis(input: String, opts: Map<String, Any>): String =
        Base64
            .Default
            .encode(input.encodeToByteArray())
            .toString()

}