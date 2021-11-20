package com.logo.logoquiz.utils

import android.content.Context
import java.io.ByteArrayOutputStream
import java.io.IOException

object StorageUtils {
    fun getRawData(context: Context?, configId:Int):String{
        val inputStream = context?.resources?.openRawResource(configId)
        val outputStream = ByteArrayOutputStream()
        val buf = ByteArray(1024)
        var len: Int?
        try {
            do {
                len = inputStream?.read(buf)
                if (len == -1)
                    break
                outputStream.write(buf, 0, len!!)
            } while (true)
            outputStream.close()
            inputStream?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return outputStream.toString()
    }
}