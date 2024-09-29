package com.nospawnn.operations.encoding

import com.nospawnn.operations.Operation
import java.awt.Component
import javax.swing.JLabel
import javax.swing.JList
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class Base64Decode : Operation() {
    override val listText = "From Base64"

    @OptIn(ExperimentalEncodingApi::class)
    override fun perform(input: String): String =
        Base64
            .Default
            .decode(input.encodeToByteArray())
            .decodeToString()

    override fun getListCellRendererComponent(
        list: JList<*>?, value: Any?, index: Int, selected: Boolean, hasFocus: Boolean
    ): Component = JLabel(listText)

}