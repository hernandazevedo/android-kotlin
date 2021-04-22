package com.hernandazevedo.zaap.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hernandazevedo.zaap.core.base.exception.Failure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

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

}