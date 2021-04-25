package com.hernandazevedo.zaap.core.base

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.hernandazevedo.zaap.core.base.log.Logger
import org.koin.android.ext.android.inject

open class BaseActivity : AppCompatActivity() {

    val logger: Logger by inject()

    protected fun showMessage(@StringRes message: Int, vararg formatArgs: Any) {
        val messageString = getString(message, *formatArgs)
        Toast.makeText(this, messageString, Toast.LENGTH_LONG).show()
        logger.error(messageString)
    }
}