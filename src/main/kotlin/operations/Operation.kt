package com.nospawnn.operations

abstract class Operation(private val listText: String) {
    open val requiredArgs: Map<String, Any> = mapOf()

    fun perform(input: String, opts: Map<String, Any>?): String {
        if (!areValidArgs(opts)) {
            println("Invalid args for \"$listText\":\n  Given $opts\n  Required $requiredArgs")
            return ""
        }

        return runThis(input, opts!!)
    }

    abstract fun runThis(input: String, opts: Map<String, Any>): String

    fun areValidArgs(passedArgs: Map<String, Any>?): Boolean {
        if ((passedArgs == null || passedArgs.isEmpty()) && requiredArgs.isEmpty()) return true
        if (passedArgs!!.size != requiredArgs.size) return false
        return passedArgs
            .filterNot { (k, _) -> requiredArgs.containsKey(k) }
            .isEmpty()
    }

    override fun toString(): String = listText
}