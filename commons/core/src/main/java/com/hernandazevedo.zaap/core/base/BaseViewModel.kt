package com.hernandazevedo.zaap.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hernandazevedo.zaap.core.exception.Failure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel(), CoroutineScope {

    var failure: MutableLiveData<Failure> = MutableLiveData()
    private val job = Job()
    override val coroutineContext = job + CoroutineDispatchers.Main

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }

    override fun onCleared() {
        super.onCleared()
        cancel()
    }

    /**
     * Invoke operator to execute UseCases running on IO thread and responding on Main thread.
     */
    operator fun <Type : Any, Params : Any> UseCase<Type, Params>.invoke(
        values: Params,
        onResult: (Result<Type, Failure>) -> Unit = {}
    ) = launch {
        val deferred = async(CoroutineDispatchers.IO) { this@invoke.run(values) }
        onResult(deferred.await())
    }
}