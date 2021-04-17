package com.hernandazevedo.operations.comparison

import com.hernandazevedo.operations.Operation
import com.hernandazevedo.operations.OperationType

internal class GtOperation : Operation, ComparisonValidationParameterOperation {

    override fun execute(vararg params: OperationType?): OperationType {
        if (parametersIsNull(params)) return OperationType.Null

        val value1 = (params[0] as OperationType.TypeNumber).value.toDouble()
        val value2 = (params[1] as OperationType.TypeNumber).value.toDouble()

        val result = value1 > value2
        return OperationType.TypeBoolean(result)
    }
}