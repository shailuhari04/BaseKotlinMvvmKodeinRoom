package com.basekotlinmvvmkodeinroom.data.network

import com.basekotlinmvvmkodeinroom.data.db.entity.User
import com.basekotlinmvvmkodeinroom.data.network.response.DeviceRegistrationResponse
import com.google.gson.JsonObject
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * REST API access points
 */
interface APIService {

    @FormUrlEncoded
    @POST("users/get_user_account")
    fun getUserAccount(
        @Field("uid") uid:String? = null,
        @Field("method") method:String? = null
    ): Deferred<User>


    @POST("api/v1/device/register")
    fun sendDeviceDetails(@Body objects: JsonObject): Deferred<DeviceRegistrationResponse>


    companion object {
        var BASE_URL = "http://"
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor, sessionValidatorInterceptor : SessionValidatorInterceptor): APIService {
            val requestInterceptor = Interceptor { chain ->
                val request = chain.request()
                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .addInterceptor(sessionValidatorInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)
        }
    }
}
