package com.nospawnn.operations.encoding

import com.nospawnn.operations.Operation
import java.awt.Component
import javax.swing.JLabel
import javax.swing.JList
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.jvm.Throws

class HexDecode : Operation() {
    override val listText = "From Hex"

    @Throws(NumberFormatException::class)
    override fun perform(input: String): String =
        input
            .chunked(3)
            .map { it.trim().toInt(16).toChar() }
            .joinToString("")
}