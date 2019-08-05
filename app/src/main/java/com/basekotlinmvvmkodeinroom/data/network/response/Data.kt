package com.basekotlinmvvmkodeinroom.data.network.response


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("deviceId")
    val deviceId: String,
    @SerializedName("token")
    val token: String
)