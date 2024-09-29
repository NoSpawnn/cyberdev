package com.nospawnn.operations.encoding

import com.nospawnn.operations.Operation
import java.awt.Component
import javax.swing.JLabel
import javax.swing.JList
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class Base64Encode : Operation() {
    override val listText = "To Base64"

    @OptIn(ExperimentalEncodingApi::class)
    override fun perform(input: String): String =
        Base64
            .Default
            .encode(input.encodeToByteArray())
            .toString()

    override fun getListCellRendererComponent(
        list: JList<*>?, value: Any?, index: Int, selected: Boolean, hasFocus: Boolean
    ): Component {
        return JLabel(listText)
    }
}