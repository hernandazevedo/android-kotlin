
package com.hernandazevedo.operations.logic

import com.hernandazevedo.operations.Operation
import com.hernandazevedo.operations.OperationType

internal class ConditionOperation : Operation, SafeGetHelper {

    override fun execute(vararg params: OperationType?): OperationType {
        val value = (params.getOrNull(0) as? OperationType.TypeBoolean)?.value
        return if (value == true) {
            safeGet(params, 1)
        } else {
            safeGet(params, 2)
        }
    }
}