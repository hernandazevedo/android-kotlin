package com.hernandazevedo.operations

import com.hernandazevedo.operations.comparison.EqOperation
import com.hernandazevedo.operations.comparison.GtOperation
import com.hernandazevedo.operations.comparison.GteOperation
import com.hernandazevedo.operations.comparison.LtOperation
import com.hernandazevedo.operations.comparison.LteOperation
import com.hernandazevedo.operations.logic.AndOperation
import com.hernandazevedo.operations.logic.ConditionOperation
import com.hernandazevedo.operations.logic.NotOperation
import com.hernandazevedo.operations.logic.OrOperation

object InternalOperationFactory {
    fun registeredOperations(): Map<String, Operation> {
        return mapOf(
                "eq" to EqOperation(),
                "gte" to GteOperation(),
                "gt" to GtOperation(),
                "lte" to LteOperation(),
                "lt" to LtOperation(),
                "and" to AndOperation(),
                "condition" to ConditionOperation(),
                "not" to NotOperation(),
                "or" to OrOperation()
                )
    }
}