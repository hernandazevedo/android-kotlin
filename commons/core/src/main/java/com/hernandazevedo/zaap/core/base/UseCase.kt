package com.hernandazevedo.zaap.core.base

import com.hernandazevedo.zaap.core.base.exception.Failure
import kotlinx.coroutines.flow.Flow

abstract class UseCase<out Type : Any, in Params : Any> {

    abstract suspend fun run(params: Params): Flow<Result<Type, Failure>>

    /**
     * To be used when you do not need to pass any parameter
     */
    class None
}