package com.hernandazevedo.zaap.core

import io.mockk.MockKVerificationScope
import io.mockk.Ordering

object MockkUtil {

    /**
     * Implements [io.mockk.verify] method with the option to add a description message in case of
     * a [AssertionError].
     */
    fun verify(
        ordering: Ordering = Ordering.UNORDERED,
        inverse: Boolean = false,
        atLeast: Int = 1,
        atMost: Int = Integer.MAX_VALUE,
        exactly: Int = 1,
        timeout: Long = -1,
        message: String? = null,
        verifyBlock: MockKVerificationScope.() -> Unit
    ) {
        try {
            io.mockk.verify(
                ordering = ordering,
                inverse = inverse,
                atLeast = atLeast,
                atMost = atMost,
                exactly = exactly,
                timeout = timeout,
                verifyBlock = verifyBlock
            )
        } catch (error: AssertionError) {
            throw AssertionError(message ?: error.message)
        }
    }

    /**
     * Implements [io.mockk.coVerify] method with the option to add a description message in case
     * of a [AssertionError].
     */
    fun coVerify(
        ordering: Ordering = Ordering.UNORDERED,
        inverse: Boolean = false,
        atLeast: Int = 1,
        atMost: Int = Integer.MAX_VALUE,
        exactly: Int = 1,
        timeout: Long = -1,
        message: String? = null,
        verifyBlock: suspend MockKVerificationScope.() -> Unit
    ) {
        try {
            io.mockk.coVerify(
                ordering = ordering,
                inverse = inverse,
                atLeast = atLeast,
                atMost = atMost,
                exactly = exactly,
                timeout = timeout,
                verifyBlock = verifyBlock
            )
        } catch (error: AssertionError) {
            throw AssertionError(message ?: error.message)
        }
    }

}