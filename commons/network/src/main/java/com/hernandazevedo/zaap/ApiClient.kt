package com.hernandazevedo.zaap

import retrofit2.Retrofit

const val BASE_URL = "http://grupozap-code-challenge.s3-website-us-east-1.amazonaws.com"
val retrofit: Retrofit = createNetworkClient(BASE_URL)