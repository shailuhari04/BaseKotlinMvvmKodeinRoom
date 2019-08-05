package com.basekotlinmvvmkodeinroom.data.provider

import com.basekotlinmvvmkodeinroom.data.db.entity.Device

interface DeviceDetailProvider {
    suspend fun getDeviceDetails(): Device
}