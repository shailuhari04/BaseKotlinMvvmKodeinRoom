package com.basekotlinmvvmkodeinroom.data.provider

import com.google.gson.JsonObject

interface EncryptedDataProvider {

    fun getEncryptedData(jsonObj: JsonObject): String?
}