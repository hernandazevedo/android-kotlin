
package com.hernandazevedo.operations.logic

import com.hernandazevedo.operations.Operation
import com.hernandazevedo.operations.OperationType

internal class OrOperation : Operation {

    override fun execute(vararg params: OperationType?): OperationType {
        params.forEach {
            if (it is OperationType.TypeBoolean && it.value) {
                return OperationType.TypeBoolean(true)
            }
        }

        return OperationType.TypeBoolean(false)
    }
}