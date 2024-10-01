package com.nospawnn.operations

import com.nospawnn.operations.ciphers.CaesarDecrypt
import com.nospawnn.operations.ciphers.CaesarEncrypt
import com.nospawnn.operations.formatting.Base64Decode
import com.nospawnn.operations.formatting.Base64Encode
import com.nospawnn.operations.formatting.HexDecode
import com.nospawnn.operations.formatting.HexEncode

object OperationsList {
    private val formatting = listOf(Base64Encode(), Base64Decode(), HexEncode(), HexDecode())
    private val encryption = listOf(CaesarEncrypt(), CaesarDecrypt())

    val operations = mapOf(Pair("Data format", formatting), Pair("Encryption", encryption))
}