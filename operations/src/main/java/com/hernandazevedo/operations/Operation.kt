package com.hernandazevedo.operations

interface Operation {
    fun execute(vararg params: OperationType?): OperationType
}