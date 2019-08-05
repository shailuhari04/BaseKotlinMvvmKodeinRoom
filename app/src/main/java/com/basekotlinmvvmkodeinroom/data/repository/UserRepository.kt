package com.basekotlinmvvmkodeinroom.data.repository

import androidx.lifecycle.LiveData
import com.basekotlinmvvmkodeinroom.data.db.entity.Device
import com.basekotlinmvvmkodeinroom.data.network.response.DeviceRegistrationResponse

interface UserRepository {
    val uploadedDeviceDetails :LiveData<DeviceRegistrationResponse>
    suspend fun sendDeviceInfo(device: Device)
}