
package com.hernandazevedo.operations.logic

import com.hernandazevedo.operations.OperationType

internal interface SafeGetHelper {

    fun safeGet(params: Array<out OperationType?>, position: Int): OperationType {
        val parameter = params.getOrNull(position)
        return parameter ?: OperationType.Null
    }
}