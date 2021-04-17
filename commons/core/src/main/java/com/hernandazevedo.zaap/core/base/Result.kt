package com.hernandazevedo.zaap.core.base

/**
 * Represents a value of one of two possible types (a disjoint union).
 * Instances of [Result] are either an instance of [Success] or [Failure].
 * [Success] is used for "success" result and [Failure] is used for "failure" result.
 *
 * @see Success
 * @see Failure
 */
sealed class Result<out SuccessValue, out FailureType> {

    /** * Represents the success side of [Result] class which by convention is a "Success". */
    data class Success<out SuccessValue>(val value: SuccessValue) : Result<SuccessValue, Nothing>()

    /** * Represents the failure side of [Result] class which by convention is a "Failure". */
    data class Failure<out FailureType>(val failure: FailureType) : Result<Nothing, FailureType>()

    fun <SuccessValue> success(value: SuccessValue) = Success(value)
    fun <FailureType> failure(failure: FailureType) = Failure(failure)

    fun either(successHandler: (SuccessValue) -> Any, failureHandler: (FailureType) -> Any): Any =
        when (this) {
            is Success -> successHandler(value)
            is Failure -> failureHandler(failure)
        }

}

/**
 * Defines a default value to a failure result as a success.
 * See also [Result] [Result.Failure] [Result.Success].
 *
 * @receiver Original [Result] that needs to be changed in case it is a failure.
 * @param value Default value that should be returned when the original [Result] turns to fail.
 * @return New success [Result] with the default value.
 */
fun <SuccessValue, FailureType> Result<SuccessValue, FailureType>.withDefault(value: SuccessValue)
        : Result.Success<SuccessValue> {
    return if (this is Result.Success) {
        this
    } else {
        Result.Success(value)
    }
}

// Credits to Alex Hart -> https://proandroiddev.com/kotlins-nothing-type-946de7d464fb
// Composes 2 functions
fun <A, B, C> ((A) -> B).c(f: (B) -> C): (A) -> C = {
    f(this(it))
}

fun <T, SuccessValue, FailureType> Result<SuccessValue, FailureType>.flatMap(
    fn: (SuccessValue) -> Result<T, FailureType>
): Result<T, FailureType> =
    when (this) {
        is Result.Success -> fn(value)
        is Result.Failure -> Result.Failure(failure)
    }

fun <T, SuccessValue, FailureType> Result<SuccessValue, FailureType>.map(
    fn: (SuccessValue) -> (T)
): Result<T, FailureType> =
    this.flatMap(fn.c(::success))
