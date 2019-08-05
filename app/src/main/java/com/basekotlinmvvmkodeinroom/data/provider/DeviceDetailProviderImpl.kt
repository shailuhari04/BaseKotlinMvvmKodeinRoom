package com.basekotlinmvvmkodeinroom.data.provider

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.TextUtils
import androidx.core.app.ActivityCompat
import com.basekotlinmvvmkodeinroom.data.db.entity.Device
import com.google.firebase.iid.FirebaseInstanceId
import java.util.*

class DeviceDetailProviderImpl(context: Context) : DeviceDetailProvider {

    private val appContext = context.applicationContext

    override suspend fun getDeviceDetails(): Device {
        val device = Device()
        device.deviceId = getDeviceID(appContext)
        device.deviceIMEINo = getDeviceImeiNo(appContext)
        device.fcmRegistrationId = FirebaseInstanceId.getInstance().getToken()
        device.deviceName  = getManufacturer()
        device.deviceModel = getDevicetModel()
        device.appVersion = getAppVersion(appContext)
        device.deviceSerialNo = getSerialNumber()
        device.deviceOS = getOSVersion()
        device.deviceLocale = getDeviceLocale()
        return device
    }

    @SuppressLint("HardwareIds")
    fun getDeviceID(context: Context): String {
        var strReturn : String
        try {
            strReturn = Settings.Secure.getString(context.contentResolver,
                Settings.Secure.ANDROID_ID)
        }catch (e:Exception) {
            strReturn =""
        }
        return strReturn

    }


    fun getDevicetModel(): String {
        return Build.MODEL
    }


    fun getManufacturer(): String {
        return Build.MANUFACTURER
    }

    fun getSerialNumber(): String {
        return Build.SERIAL
    }

    fun getOSVersion(): String {
        return Build.VERSION.RELEASE
    }

    @SuppressLint("HardwareIds")
    fun getDeviceImeiNo(context: Context): String {
        try {
            val telephonyManager = context.getSystemService(
                Context.TELEPHONY_SERVICE) as TelephonyManager
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return ""
            }
            val strimeiNo = telephonyManager.deviceId
            return if (TextUtils.isEmpty(strimeiNo)) {
                ""
            } else {
                strimeiNo
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }

    }

    fun getAppVersion(context: Context): String {
        var currentVersion = ""
        val pm = context.packageManager
        var pInfo: PackageInfo? = null

        try {
            pInfo = pm.getPackageInfo(context.packageName, 0)

        } catch (e1: PackageManager.NameNotFoundException) {
            e1.printStackTrace()
        }

        currentVersion = pInfo!!.versionName
        return currentVersion
    }

    fun getDeviceLocale() :String{
        return Locale.getDefault().getLanguage();
    }
}