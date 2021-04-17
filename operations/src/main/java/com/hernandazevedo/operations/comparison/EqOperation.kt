package com.hernandazevedo.operations.comparison
import com.hernandazevedo.operations.Operation
import com.hernandazevedo.operations.OperationType

internal class EqOperation : Operation {

    override fun execute(vararg params: OperationType?): OperationType {
        val result = params[0].toString() == params[1].toString()

        return OperationType.TypeBoolean(result)
    }
}