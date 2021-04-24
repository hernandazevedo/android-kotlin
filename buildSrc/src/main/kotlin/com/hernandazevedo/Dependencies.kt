package com.hernandazevedo

import org.gradle.api.Plugin
import org.gradle.api.Project

class Dependencies : Plugin<Project> {
    override fun apply(project: Project) {}

    object ApplicationId {
        const val id = "com.hernandazevedo.zaap"
    }

    object MainModules {
        const val app = ":app"
        const val operations = ":operations"
    }

    object CommonsModules {
        const val network = ":commons:network"
        const val core = ":commons:core"
    }

    object FeatureModules {
        const val searchProperty = ":features:search-property"
        const val detailProperty = ":features:detail-property"
        val asList = listOf(searchProperty, detailProperty)
    }

    object Releases {
        const val versionCode = 1
        const val versionName = "1.0"
    }

    object GeneralNames {
        const val testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
        const val consumerProguard = "consumer-rules.pro"
    }

    object Versions {

        const val compileSdk = 30
        const val minSdk = 19
        const val targetSdk = 30
        const val buildTools = "30.0.0"
        const val kotlin = "1.4.10"
        const val koin = "2.0.1"

        const val kotlinCoroutines = "1.4.2"
        const val kotlinCoroutinesAdapter = "0.9.2"
        const val kotlinTest = "1.3.50"

        const val kotlinPoet = "1.7.1"
        const val okio = "2.9.0"

        const val glide = "4.9.0"

        const val appcompat = "1.2.0"
        const val viewModel = "2.3.0"
        const val recyclerView = "1.2.0-beta01"
        const val constraintlayout = "2.0.2"
        const val pagingRuntime = "3.0.0-beta01"

        const val moshi = "1.11.0"
        const val jsonObject = "20200518"

        const val retrofit = "2.9.0"
        const val okHttp = "4.7.0"

        const val loggingInterceptor = "4.7.2"

        const val junit5 = "5.7.0"
        const val junit4 = "4.13"

        const val kotlinCoroutinesTest = "1.3.9"
        const val materialDesign = "1.2.1"
        const val googleCompileTesting = "0.18"
        const val googleAutoService = "1.0-rc7"

        const val mockk = "1.10.2"

        const val testRunner = "1.3.1-alpha02"
        const val testExt = "1.1.3-alpha02"
        const val espresso = "3.4.0-alpha02"
        const val archCoreTesting = "2.1.0"
        const val testRules = "1.3.1-alpha02"
        const val testCore = "1.3.1-alpha02"
        const val robolectric = "4.3"

        const val incap = "0.3"

        const val cucumber = "1.2.6"
        const val kotlinCompileTesting = "1.3.1"

        const val multidex = "2.0.1"
    }

    object GlideLibraries {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }

    object GeneralLibraries {
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val kotlinCoroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"

        const val kotlinCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"

        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"

        const val okHttp = "com.squareup.okhttp3:okhttp-urlconnection:${Versions.okHttp}"

        const val loggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
        const val kotlinCoroutinesAdapter =
            "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.kotlinCoroutinesAdapter}"

        const val koin = "org.koin:koin-androidx-viewmodel:${Versions.koin}"


        const val jsonObject = "org.json:json:${Versions.jsonObject}"

        const val multidex = "androidx.multidex:multidex:${Versions.multidex}"
    }

    object GoogleLibraries {
        const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
    }

    object AndroidxLibraries {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.appcompat}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModel}"

        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.viewModel}"

        const val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.viewModel}"

        const val constraintlayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"

        const val pagingRuntime = "androidx.paging:paging-runtime-ktx:${Versions.pagingRuntime}"

    }

    object MoshiLibraries {
        const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
        const val kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
        const val adapters = "com.squareup.moshi:moshi-adapters:${Versions.moshi}"
    }

    object TestLibraries {
        const val junit4 = "junit:junit:${Versions.junit4}"
        const val junitApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junit5}"
        const val junitEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit5}"
        const val junitVintageEngine = "org.junit.vintage:junit-vintage-engine:${Versions.junit5}"
        const val mockk = "io.mockk:mockk:${Versions.mockk}"
        const val kotlinCoroutinesTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCoroutinesTest}"
        const val googleCompileTesting =
            "com.google.testing.compile:compile-testing:${Versions.googleCompileTesting}"
        const val archCoreTesting = "androidx.arch.core:core-testing:${Versions.archCoreTesting}"
        const val testRunner = "androidx.test:runner:${Versions.testRunner}"
        const val testExt = "androidx.test.ext:junit:${Versions.testExt}"
        const val testCore = "androidx.test:core:${Versions.testCore}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
        const val testRules = "androidx.test:rules:${Versions.testRules}"
        const val cucumberAndroid = "info.cukes:cucumber-android:${Versions.cucumber}"
        const val cucumberPicocontainer = "info.cukes:cucumber-picocontainer:${Versions.cucumber}"
        const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
        const val kotlinCompileTesting =
            "com.github.tschuchortdev:kotlin-compile-testing:${Versions.kotlinCompileTesting}"
        const val koin = "org.koin:koin-test:${Versions.koin}"
        const val kotlinTest = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlinTest}"
    }
}