package com.nospawnn.operations

import com.nospawnn.operations.ciphers.*
import com.nospawnn.operations.formatting.*

object OperationsList {
    private val formatting = listOf(Base64Encode(), Base64Decode(), HexEncode(), HexDecode())
    private val encryption = listOf(CaesarEncrypt(), CaesarDecrypt())

    val operations = mapOf("Data format" to formatting, "Encryption" to encryption)
}