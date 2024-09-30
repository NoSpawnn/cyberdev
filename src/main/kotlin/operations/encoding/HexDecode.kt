package com.nospawnn.operations.encoding

import com.nospawnn.operations.Operation
import java.awt.Component
import javax.swing.JLabel
import javax.swing.JList
import kotlin.io.encoding.ExperimentalEncodingApi

class HexDecode : Operation() {
    override val listText = "From Hex"

    override fun perform(input: String): String =
        input
            .chunked(3)
            .map { it.trim().toInt(16).toChar() }
            .joinToString("")

}