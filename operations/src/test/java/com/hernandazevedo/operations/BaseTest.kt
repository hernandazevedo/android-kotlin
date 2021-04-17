package com.hernandazevedo.operations

import com.hernandazevedo.operations.log.Logger
import io.mockk.MockKAnnotations
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

abstract class BaseTest {

    protected val logger = mockk<Logger>(relaxed = true)
    @BeforeEach
    open fun setUp() {
        MockKAnnotations.init(this)

    }

    @AfterEach
    open fun tearDown() {
        unmockkAll()
    }
}
