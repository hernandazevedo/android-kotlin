package com.hernandazevedo.operations.comparison
import com.hernandazevedo.operations.Operation
import com.hernandazevedo.operations.OperationType

internal class GteOperation : Operation, ComparisonValidationParameterOperation {

    override fun execute(vararg params: OperationType?): OperationType {
        if (parametersIsNull(params)) return OperationType.Null

        val value1 = (params[0] as OperationType.TypeNumber).value
        val value2 = (params[1] as OperationType.TypeNumber).value

        val result = value1.toDouble() >= value2.toDouble()
        return OperationType.TypeBoolean(result)
    }
}