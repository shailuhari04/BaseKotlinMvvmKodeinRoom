package com.basekotlinmvvmkodeinroom.data.network.response


import com.google.gson.annotations.SerializedName

data class DeviceRegistrationResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)