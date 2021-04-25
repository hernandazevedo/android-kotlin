package com.hernandazevedo.zaap.core.base.log

import android.util.Log
private const val TAG = "Zaap"

class DefaultLogger: Logger {
    override fun warning(message: String) {
        Log.w(TAG, message)
    }

    override fun error(message: String) {
        Log.e(TAG, message)
    }

    override fun error(message: String, throwable: Throwable) {
        Log.e(TAG, message, throwable)
    }

    override fun info(message: String) {
        Log.i(TAG, message)
    }
}