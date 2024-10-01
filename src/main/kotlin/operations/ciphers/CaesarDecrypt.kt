package com.nospawnn.operations.ciphers

import com.nospawnn.operations.Operation

class CaesarDecrypt : Operation("Shift Cipher decrypt") {
    private val defaultAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    override val requiredArgs =
        mapOf("alphabet" to defaultAlphabet, "preserveCase" to true, "offset" to 1)

    override fun runThis(
        text: String,
        opts: Map<String, Any>,
    ): String {
        val alphabet = opts["alphabet"] as String
        val preserveCase = opts["preserveCase"] as Boolean
        val offset = -(opts["offset"] as Int)

        return text.map { c ->
            if (preserveCase)
                if (c.isUpperCase()) c.shiftBy(offset, alphabet)
                else c.shiftBy(offset, alphabet).lowercaseChar()
            else
                c.shiftBy(offset, alphabet)
        }.joinToString("")
    }

    private fun Char.shiftBy(n: Int, alphabet: String): Char =
    // This was originally `(alphabet.indexOf(this, ignoreCase = true) + n) % alphabet.length`,
    // but that failed with negative keys (i.e. decrypt). I don't know why this altered version works,
        // but it does
        alphabet[(alphabet.indexOf(this, ignoreCase = true) + n).mod(alphabet.length)]

}