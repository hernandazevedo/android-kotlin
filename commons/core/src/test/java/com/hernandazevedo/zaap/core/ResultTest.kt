package com.hernandazevedo.zaap.core

import com.hernandazevedo.zaap.core.base.Result
import com.hernandazevedo.zaap.core.base.withDefault
import com.hernandazevedo.zaap.core.base.exception.Failure
import com.hernandazevedo.zaap.core.MockkUtil.verify
import io.mockk.spyk
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ResultTest {

    @Test
    fun successResult_either_shouldCallSuccessHandler() {
        val successValue = "value"
        val successHandler = spyk<(Any) -> Any>()
        val failureHandler = spyk<(Any) -> Any>()
        val result = Result.Success(successValue)

        result.either(successHandler, failureHandler)

        verify(
            message = "The success handler function should have been called in a success result"
        ) {
            successHandler(result.value)
        }
        verify(
            exactly = 0,
            message = "The failure handler function should not have been called in a success result"
        ) {
            failureHandler(any())
        }
    }

    @Test
    fun failureResult_either_shouldCallFailureHandler() {
        val failureType = Failure.GenericError("")
        val successHandler = spyk<(Any) -> Any>()
        val failureHandler = spyk<(Any) -> Any>()
        val result = Result.Failure(failureType)

        result.either(successHandler, failureHandler)

        verify(
            exactly = 0,
            message = "The success handler function should not have been called in a failure result"
        ) {
            successHandler(any())
        }
        verify(
            message = "The failure handler function should have been called in a failure result"
        ) {
            failureHandler(result.failure)
        }
    }

    @Test
    fun successResult_withDefault_shouldRemainUnmodified() {
        val originalValue = "original"
        val defaultValue = "default"

        val originalResult = Result.Success(originalValue)
        val newResult = originalResult.withDefault(defaultValue)

        assertEquals(
            originalResult.value,
            newResult.value,
            "A success result should not have its value modified to the default value."
        )
        assertEquals(
            originalResult,
            newResult,
            "A success result should remain the same as before."
        )
    }

    @Test
    fun failureResult_withDefault_shouldBeModified() {
        val defaultValue = "default"

        val originalResult = Result.Failure(Failure.GenericError(""))
        val newResult = originalResult.withDefault(defaultValue)

        assertTrue(
            newResult is Result.Success,
            "A failure result should always be converted to a success one."
        )
        assertEquals(
            defaultValue,
            newResult.value,
            "A failure result should have its value modified to the default value."
        )
    }

}