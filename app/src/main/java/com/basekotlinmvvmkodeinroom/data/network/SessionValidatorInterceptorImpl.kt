package com.basekotlinmvvmkodeinroom.data.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class SessionValidatorInterceptorImpl(context: Context) : SessionValidatorInterceptor
{
    private val appContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val response = chain.proceed(request)
        if(response.code ==701) {
            handleSessionExpiredResponse()
        }
        return response
    }

    private fun handleSessionExpiredResponse() {
        TODO("Handle Session Here....")
    }


}