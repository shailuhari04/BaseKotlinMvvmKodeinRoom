package com.basekotlinmvvmkodeinroom.utilities

import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


object EncryptionManager {

    /**
     * Used to Encrypt the byte array supplied
     * @param data
     * byte array of complete file
     * @param key
     * Secret key
     */
    @Throws(Exception::class)
    fun encrypt(data: ByteArray, tempKey: String, tempIV: String): ByteArray {
        var key = tempKey
        var strIV = tempIV
        key = keyPadding(key)
        strIV = keyPadding(strIV)
        val skeySpec = SecretKeySpec(key.toByteArray(), "AES")
        val iv = strIV.toByteArray()
        val ivspec = IvParameterSpec(iv)
        val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivspec)
        return cipher.doFinal(data)
    }

    /**
     * Used to Decrypt the byte array supplied
     * @param data
     * byte array of complete file
     * @param key
     * Secret key
     */
    @Throws(Exception::class)
    fun decrypt(data: ByteArray, tempKey: String, tempIV: String): ByteArray {
        var key = tempKey
        var striv = tempIV
        key = keyPadding(key)
        striv = keyPadding(striv)
        val skeySpec = SecretKeySpec(key.toByteArray(), "AES")
        val iv = striv.toByteArray()
        val ivspec = IvParameterSpec(iv)
        val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivspec)
        return cipher.doFinal(data)
    }

    fun keyPadding(str: String): String {
        val sb = StringBuilder(str)
        if (sb.length < 16) {
            while (sb.length < 16) {
                sb.append("0")
            }
            return sb.toString()
        } else {
            return sb.substring(0, 16)
        }
    }
}
