package com.hernandazevedo.operations

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested

@DisplayName("Given an OperationResolver object")
internal class OperationResolverTest : BaseTest() {

    private lateinit var operationResolver: OperationResolver

    @BeforeEach
    override fun setUp() {
        super.setUp()
        operationResolver = OperationResolver(logger = logger)
    }

    @DisplayName("When execute method is called passing eq operation")
    @Nested
    inner class EqOperation {

        @DisplayName("Then it should execute the operation correctly")
        @Test
        fun shouldExecuteOperation() {
            assertNotNull(operationResolver.execute("eq", 1, 1))
        }
    }

    @DisplayName("When execute method is called passing gt operation")
    @Nested
    inner class GtOperation {

        @DisplayName("Then it should execute the operation correctly")
        @Test
        fun shouldExecuteOperation() {
            assertNotNull(operationResolver.execute("gt", 1, 1))
        }
    }

    @DisplayName("When execute method is called passing gte operation")
    @Nested
    inner class GteOperation {

        @DisplayName("Then it should execute the operation correctly")
        @Test
        fun shouldExecuteOperation() {
            assertNotNull(operationResolver.execute("gte", 1, 1))
        }
    }

    @DisplayName("When execute method is called passing lt operation")
    @Nested
    inner class LtOperation {

        @DisplayName("Then it should execute the operation correctly")
        @Test
        fun shouldExecuteOperation() {
            assertNotNull(operationResolver.execute("lt", 1, 1))
        }
    }

    @DisplayName("When execute method is called passing lte operation")
    @Nested
    inner class LteOperation {

        @DisplayName("Then it should execute the operation correctly")
        @Test
        fun shouldExecuteOperation() {
            assertNotNull(operationResolver.execute("lte", 1, 1))
        }
    }

    @DisplayName("When execute method is called passing and operation")
    @Nested
    inner class AndOperation {

        @DisplayName("Then it should execute the operation correctly")
        @Test
        fun shouldExecuteOperation() {
            assertNotNull(operationResolver.execute("and", true))
        }
    }

    @DisplayName("When execute method is called passing or operation")
    @Nested
    inner class OrOperation {

        @DisplayName("Then it should execute the operation correctly")
        @Test
        fun shouldExecuteOperation() {
            assertNotNull(operationResolver.execute("or", true))
        }
    }

    @DisplayName("When execute method is called passing not operation")
    @Nested
    inner class NotOperation {

        @DisplayName("Then it should execute the operation correctly")
        @Test
        fun shouldExecuteOperation() {
            assertNotNull(operationResolver.execute("not", true))
        }
    }

    @DisplayName("When execute method is called passing condition operation")
    @Nested
    inner class ConditionOperation {

        @DisplayName("Then it should execute the operation correctly")
        @Test
        fun shouldExecuteOperation() {
            assertNotNull(operationResolver.execute("condition", true, true, true))
        }
    }

    @DisplayName("When execute method is called passing a non existent operation")
    @Nested
    inner class NonExistentOperation {

        @DisplayName("Then it should log that this operation does not exist")
        @Test
        fun shouldLogThaOperationDoesNotExist() {

            assertNull(operationResolver.execute("aa", ""))

        }
    }

}