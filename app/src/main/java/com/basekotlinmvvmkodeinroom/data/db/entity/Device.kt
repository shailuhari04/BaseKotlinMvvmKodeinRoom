package com.basekotlinmvvmkodeinroom.data.db.entity

import com.google.gson.JsonObject

class Device{
    var deviceId:String? = null
    var deviceOS:String? = null
    var deviceName:String? = null
    var deviceModel:String? = null
    var deviceVersion:String? = null
    var deviceSerialNo:String? = null
    var deviceIMEINo:String? = null
    var deviceLocale:String? = null
    var fcmRegistrationId:String? = null
    var appVersion:String? = null

fun toJSON(): JsonObject {
    val jsonObject = JsonObject()
    try {
        jsonObject.addProperty("DeviceIMEI",deviceIMEINo)
        jsonObject.addProperty("DeviceId", deviceId)
        jsonObject.addProperty("DeviceOS",deviceOS)
        jsonObject.addProperty("DeviceVersion",deviceVersion)
        jsonObject.addProperty("DeviceSerialNo", deviceSerialNo)
        jsonObject.addProperty("DeviceModel", deviceModel)
        jsonObject.addProperty("AppVersion", appVersion)
        jsonObject.addProperty("FCMRegistrationId", fcmRegistrationId)
        jsonObject.addProperty("DeviceLocale", deviceLocale)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return  jsonObject
}
}