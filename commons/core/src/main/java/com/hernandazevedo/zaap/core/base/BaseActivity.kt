package com.hernandazevedo.zaap.core.base

import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    protected fun showMessage(@StringRes message: Int, vararg formatArgs: Any) {
        val messageString = getString(message, *formatArgs)
        Toast.makeText(this, messageString, Toast.LENGTH_LONG).show()
        Log.e("LOG_ERROR", messageString)
    }
}