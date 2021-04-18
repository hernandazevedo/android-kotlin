package com.hernandazevedo.zaap.di

import com.hernandazevedo.zaap.search.di.searchFeatureModule

/**
 * List of modules to be injected via Koin in [com.hernandazevedo.zaap.ZaapApplication].
 */
val zaapModules = listOf(
    // Application Modules
    applicationModule,

    // Feature Modules
    searchFeatureModule
)