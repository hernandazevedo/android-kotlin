package com.hernandazevedo.operations

import com.hernandazevedo.operations.exception.OperationException
import com.hernandazevedo.operations.log.DefaultLogger
import com.hernandazevedo.operations.log.Logger

class OperationResolver(
    //TODO implement a way to externalize the registration of new operations
    private val functions: Map<String, Operation> = InternalOperationFactory.registeredOperations(),
    private val logger: Logger = DefaultLogger()
) {

    fun execute(functionName: String, vararg params: Any?): Any? {
        val function = functions[functionName]

        val paramsMapped = params.map { parameter ->
            if (parameter == null) {
                return@map OperationType.Null
            }

            when (parameter) {
                is String -> OperationType.TypeString(parameter)
                is Number -> OperationType.TypeNumber(parameter)
                is Boolean -> OperationType.TypeBoolean(parameter)
                else -> throw OperationException("type not mapped")
            }
        }

        if (function == null) {
            logger.warning("Function with named $functionName does not exist.")
        }

        val result = function?.execute(*paramsMapped.toTypedArray())
        return result?.value
    }
}