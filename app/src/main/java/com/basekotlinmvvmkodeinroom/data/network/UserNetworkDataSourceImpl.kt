package com.basekotlinmvvmkodeinroom.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.basekotlinmvvmkodeinroom.data.network.response.DeviceRegistrationResponse
import com.basekotlinmvvmkodeinroom.data.provider.EncryptedDataProvider
import com.basekotlinmvvmkodeinroom.internal.NoConnectivityException
import com.google.gson.JsonObject

class UserNetworkDataSourceImpl(
    private val apiService: APIService,
    private val encryptedDataProvider: EncryptedDataProvider
): UserNetworkDataSource {

    private val _uploadedDeviceDetails = MutableLiveData<DeviceRegistrationResponse>()
    override val uploadedDeviceDetails: LiveData<DeviceRegistrationResponse>
        get() = _uploadedDeviceDetails
    override suspend fun sendDeviceDetailData(strBody: JsonObject) {
        try {
            val objJson = JsonObject()
            val strEncResponse= encryptedDataProvider.getEncryptedData(strBody)
            objJson.addProperty("dataEnc",strEncResponse)


            val sendDeviceDetailResponse = apiService
                .sendDeviceDetails(objJson)
                .await()
            _uploadedDeviceDetails.postValue(sendDeviceDetailResponse)
    }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }
}