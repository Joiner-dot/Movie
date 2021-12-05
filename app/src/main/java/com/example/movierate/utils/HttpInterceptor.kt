package com.example.movierate.utils

import com.example.movierate.api.objects.secondary.HttpConstants.API_KEY
import com.example.movierate.api.objects.secondary.HttpConstants.API_VALUE
import okhttp3.Interceptor
import okhttp3.Response

class HttpInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(API_KEY, API_VALUE)
            .build()

        return chain.proceed(request)
    }
}