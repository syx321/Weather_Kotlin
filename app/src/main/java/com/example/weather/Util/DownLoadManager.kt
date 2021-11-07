package com.example.weather.Util

import android.util.Log
import com.example.weather.model.DownLoadHandler
import com.example.weather.model.DownLoadHandler.RequestType
import okhttp3.Request
import okhttp3.OkHttpClient
import java.lang.Exception
import kotlin.concurrent.thread

class DownLoadManager {
    companion object {
        val share = DownLoadManager()
        const val KEY_API = "c46e451ddaff883a3c90c8674f2e9459"
    }

    val client = OkHttpClient()

    interface CallBack {
        fun getResult(result: String)
    }

    fun getCity(city: String, callBack: CallBack) {
        thread {
            val request = Request.Builder()
                .url(
                    "https://restapi.amap.com/v3/config/district?keywords=" +
                            city +
                            "&subdistrict=1&key=" + KEY_API
                )
                .build()
            val response = client.newCall(request).execute()
            val responseData = response.body?.string()
            if (responseData != null) {
                callBack.getResult(responseData)
            }
        }
    }

    fun getCurrentCity(city: String, callBack: CallBack) {

    }

    fun getWeather(cityCode: String, type: String, callBack: CallBack) {
        thread {
            val request = Request.Builder()
                .url(
                    "https://restapi.amap.com/v3/weather/weatherInfo?city=" +
                            cityCode +
                            "&extensions=" + type +
                            "&key=" + KEY_API
                )
                .build()
            try {
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null) {
                    callBack.getResult(responseData)
                }
            } catch (e: Exception) {
                Log.e("getWeatherERR",e.toString())
            }
        }
    }
}