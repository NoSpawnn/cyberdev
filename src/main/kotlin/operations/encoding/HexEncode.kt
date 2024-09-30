package com.nospawnn.operations.encoding

import com.nospawnn.operations.Operation
import java.awt.Component
import javax.swing.JLabel
import javax.swing.JList
import kotlin.io.encoding.ExperimentalEncodingApi

class HexEncode : Operation() {
    override val listText = "To Hex"

    @OptIn(ExperimentalEncodingApi::class)
    override fun perform(input: String): String =
        input
            .toByteArray()
            .joinToString(" ") { String.format("%02x", it) }

}