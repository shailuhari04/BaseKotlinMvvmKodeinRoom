package com.basekotlinmvvmkodeinroom.data.network

import androidx.lifecycle.LiveData
import com.basekotlinmvvmkodeinroom.data.network.response.DeviceRegistrationResponse
import com.google.gson.JsonObject

interface UserNetworkDataSource {
    val uploadedDeviceDetails :LiveData<DeviceRegistrationResponse>
    suspend fun sendDeviceDetailData(strBody:JsonObject)

}