package com.hernandazevedo.operations.comparison

import com.hernandazevedo.operations.OperationType

internal interface ComparisonValidationParameterOperation {

    fun parametersIsNull(params: Array<out OperationType?>): Boolean =
        params.isNullOrEmpty() || checkItemsInParameterIsNull(params)

    private fun checkItemsInParameterIsNull(params: Array<out OperationType?>): Boolean =
        params[0] is OperationType.Null || params[1] is OperationType.Null
}