package com.example.weather.Util

import okhttp3.Request
import okhttp3.OkHttpClient
import kotlin.concurrent.thread
import com.example.weather.Util.Utils.KEY_API



class DownLoadManager {
    companion object {
        val share = DownLoadManager()
    }

    interface CallBack {
        fun getResult(result: String)
    }
    fun getCity(city: String, callBack: CallBack) {
        val client = OkHttpClient()
        thread {
            val request = Request.Builder()
                .url("https://restapi.amap.com/v3/config/district?keywords="+
                        city+
                        "&subdistrict=1&key="+KEY_API)
                .build()
            val response = client.newCall(request).execute()

            val responseData = response.body?.string()
            if (responseData != null) {
                callBack.getResult(responseData)
            }
        }
    }
}