package com.hernandazevedo.zaap.di

import com.hernandazevedo.zaap.core.base.common.InternetValidator
import com.hernandazevedo.zaap.core.base.common.ResourceManager
import com.hernandazevedo.zaap.core.base.log.DefaultLogger
import com.hernandazevedo.zaap.core.base.log.Logger
import com.hernandazevedo.zaap.core.base.navigation.IntentFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val applicationModule = module(override = true) {

    single {
        InternetValidator(context = androidContext())
    }

    single {
        ResourceManager(context = androidContext())
    }

    single {
        IntentFactory(context = androidContext())
    }

    single {
        DefaultLogger() as Logger
    }

}
