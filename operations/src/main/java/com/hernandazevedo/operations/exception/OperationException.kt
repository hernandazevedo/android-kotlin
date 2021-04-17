package com.hernandazevedo.operations.exception

open class OperationException
constructor(
        override val message: String,
        override val cause: Throwable? = null
) : Exception(message, cause)