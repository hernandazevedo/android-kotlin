package com.hernandazevedo.operations

sealed class OperationType(open val value: Any?) {
    data class TypeString(override val value: String) : OperationType(value)
    data class TypeBoolean(override val value: Boolean) : OperationType(value)
    data class TypeNumber(override val value: Number) : OperationType(value)

    object Null : OperationType(null)
}