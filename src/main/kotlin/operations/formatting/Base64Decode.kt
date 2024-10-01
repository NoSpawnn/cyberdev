package com.nospawnn.operations.formatting

import com.nospawnn.operations.Operation
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.jvm.Throws

class Base64Decode : Operation("From Base64") {
    @OptIn(ExperimentalEncodingApi::class)
    @Throws(IllegalArgumentException::class)
    override fun runThis(input: String, opts: Map<String, Any>): String =
        Base64
            .Default
            .decode(input.encodeToByteArray())
            .decodeToString()

}