package com.hernandazevedo.operations.logic

import com.hernandazevedo.operations.Operation
import com.hernandazevedo.operations.OperationType

internal class NotOperation : Operation, SafeGetHelper {

    override fun execute(vararg params: OperationType?): OperationType {
        val value = (params[0] as OperationType.TypeBoolean).value

        val result = safeGet(params, 0)

        val tryGetBoolean = (result as? OperationType.TypeBoolean)?.value

        return if (tryGetBoolean != null) OperationType.TypeBoolean(!value) else OperationType.Null
    }
}