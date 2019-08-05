package com.basekotlinmvvmkodeinroom.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.basekotlinmvvmkodeinroom.data.provider.DeviceDetailProvider
import com.basekotlinmvvmkodeinroom.data.repository.UserRepository


class LoginViewModelFactory(
    private val userRepository: UserRepository,
    private val deviceDetailProvider: DeviceDetailProvider
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(userRepository,deviceDetailProvider) as T
    }
}