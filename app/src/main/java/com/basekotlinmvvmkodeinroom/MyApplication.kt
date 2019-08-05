package com.basekotlinmvvmkodeinroom

import android.app.Application
import android.content.Context
import android.preference.PreferenceManager
import com.basekotlinmvvmkodeinroom.data.db.RoomDB
import com.basekotlinmvvmkodeinroom.data.network.*
import com.basekotlinmvvmkodeinroom.data.provider.DeviceDetailProvider
import com.basekotlinmvvmkodeinroom.data.provider.DeviceDetailProviderImpl
import com.basekotlinmvvmkodeinroom.data.provider.EncryptedDataProvider
import com.basekotlinmvvmkodeinroom.data.provider.EncryptedDataProviderImpl
import com.basekotlinmvvmkodeinroom.data.repository.UserRepository
import com.basekotlinmvvmkodeinroom.data.repository.UserRepositoryImpl
import com.basekotlinmvvmkodeinroom.ui.login.LoginViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MyApplication : Application(),KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MyApplication))

        bind() from singleton { RoomDB(instance()) }
        bind() from singleton { instance<RoomDB>().userDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind<SessionValidatorInterceptor>() with singleton { SessionValidatorInterceptorImpl(instance()) }
        bind() from singleton { APIService(instance(),instance()) }
        bind<UserNetworkDataSource>() with singleton { UserNetworkDataSourceImpl(instance(),instance()) }
        bind<UserRepository>() with singleton { UserRepositoryImpl(instance(), instance()) }
        bind() from provider { LoginViewModelFactory(instance(), instance()) }
        bind<DeviceDetailProvider>() with singleton { DeviceDetailProviderImpl(instance()) }
        bind<EncryptedDataProvider>() with singleton { EncryptedDataProviderImpl(instance()) }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
      //  MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
    }
}