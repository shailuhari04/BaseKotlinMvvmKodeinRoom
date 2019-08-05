package com.basekotlinmvvmkodeinroom.ui.login

import androidx.lifecycle.ViewModel
import com.basekotlinmvvmkodeinroom.data.provider.DeviceDetailProvider
import com.basekotlinmvvmkodeinroom.data.repository.UserRepository
import com.basekotlinmvvmkodeinroom.internal.lazyDeferred

class LoginViewModel(
    private val userRepository: UserRepository,
    private val deviceDetailProvider: DeviceDetailProvider
): ViewModel() {

    val deviceUploadResponse = userRepository.uploadedDeviceDetails

    val deviceuploadResponse by lazyDeferred {
        userRepository.sendDeviceInfo(deviceDetailProvider.getDeviceDetails())
    }


}
