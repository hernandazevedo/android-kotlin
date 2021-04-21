package com.hernandazevedo.operations.logic

import com.hernandazevedo.operations.Operation
import com.hernandazevedo.operations.OperationType

internal class AndOperation : Operation {

    override fun execute(vararg params: OperationType?): OperationType {
        params.forEach {
            if (it is OperationType.TypeBoolean && !it.value) return OperationType.TypeBoolean(false)
        }

        val paramsIsNotEmpty = !params.isNullOrEmpty()

        return OperationType.TypeBoolean(paramsIsNotEmpty)
    }
}