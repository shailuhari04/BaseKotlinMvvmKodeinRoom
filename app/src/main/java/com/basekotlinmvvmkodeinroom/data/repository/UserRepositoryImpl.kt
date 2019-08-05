package com.basekotlinmvvmkodeinroom.data.repository

import androidx.lifecycle.LiveData
import com.basekotlinmvvmkodeinroom.data.db.dao.UserDao
import com.basekotlinmvvmkodeinroom.data.db.entity.Device
import com.basekotlinmvvmkodeinroom.data.network.UserNetworkDataSource
import com.basekotlinmvvmkodeinroom.data.network.response.DeviceRegistrationResponse

class UserRepositoryImpl(
    private val userDao: UserDao,
    private val userNetworkDataSource: UserNetworkDataSource
) : UserRepository {
    override val uploadedDeviceDetails: LiveData<DeviceRegistrationResponse>
        get() = userNetworkDataSource.uploadedDeviceDetails

    override suspend fun sendDeviceInfo(device: Device){
            userNetworkDataSource.sendDeviceDetailData(device.toJSON())
    }

}