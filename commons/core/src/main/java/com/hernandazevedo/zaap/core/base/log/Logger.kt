package com.hernandazevedo.zaap.core.base.log

interface Logger {
    /**
     * Function to pass warning message.
     */
    fun warning(message: String)

    /**
     * Function to pass error message.
     */
    fun error(message: String)

    /**
     * Function to pass a throwable and error message.
     */
    fun error(message: String, throwable: Throwable)

    /**
     * Function for passing an information message.
     */
    fun info(message: String)
}