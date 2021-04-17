package com.hernandazevedo.zaap.core.exception

sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()
    class GenericError(val message: String) : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()
}
