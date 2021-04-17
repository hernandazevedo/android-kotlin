package com.hernandazevedo.zaap.core.base

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    protected fun showMessage(@StringRes message: Int, vararg formatArgs: Any) {
        Toast.makeText(this, getString(message, *formatArgs), Toast.LENGTH_LONG).show()
    }
}