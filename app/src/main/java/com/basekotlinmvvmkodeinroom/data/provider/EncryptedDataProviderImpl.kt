package com.basekotlinmvvmkodeinroom.data.provider

import android.content.Context
import com.basekotlinmvvmkodeinroom.utilities.Base64
import com.basekotlinmvvmkodeinroom.utilities.EncryptionManager
import com.google.gson.JsonObject

class EncryptedDataProviderImpl(context: Context) : EncryptedDataProvider {
    private val appContext = context.applicationContext

    override fun getEncryptedData(jsonObj: JsonObject): String? {
        try {
            val byteResponse = jsonObj.toString().toByteArray()
            val key = "jc82dk4ka2-vd3na"
            val strIV ="b7e2a88505ff8d61"
            val byteDecRespons = EncryptionManager.encrypt(byteResponse, key, strIV)
            return  Base64.encodeBytes(byteDecRespons)
        }
        catch (e: Exception) {
          return null
        }


    }
}