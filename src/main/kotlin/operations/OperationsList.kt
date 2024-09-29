package com.nospawnn.operations

import com.nospawnn.operations.encoding.*

object OperationsList {
    private val encodeOperations = listOf(Base64Encode(), Base64Decode(), HexEncode(), HexDecode())

    val operations = mapOf(Pair("Data format", encodeOperations))
}