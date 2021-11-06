package com.example.weather.model

import android.util.Log
import com.example.weather.Util.DownLoadManager
import com.example.weather.model.DownLoadHandler.RequestType.current
import com.example.weather.model.DownLoadHandler.RequestType.forecast
import org.json.JSONArray
import org.json.JSONObject

class DownLoadHandler {
    object RequestType {
        val current = "base"
        val forecast = "all"
    }

    interface CityCallBack {
        fun getResult(result: ArrayList<Location>)
    }

    interface FinishCallBack {
        fun onFinish(forecast: Boolean = false, current: Boolean = false)
    }

    companion object {
        val share = DownLoadHandler()
    }

    val download = DownLoadManager.share
    fun getLocation(callBack: CityCallBack) {
        download.getCity("中国", object : DownLoadManager.CallBack {
            override fun getResult(result: String) {
//                Log.d("responseData", result)
                getNameFromJson(result)?.let { callBack.getResult(it) }
            }
        })
    }

    private fun getNameFromJson(json: String): ArrayList<Location>? {
        try {
            val arrayList = ArrayList<Location>()
            val all = JSONObject(json).get("districts") as JSONArray
            val city = all.get(0) as JSONObject
            val provinces = city.get("districts") as JSONArray
            for (i in 0 until provinces.length()) {
                val jsonObject = provinces.getJSONObject(i)
                val cityName = jsonObject.getString("name")//name
                val id = jsonObject.getString("adcode")
                arrayList.add(Location(cityName, id))
            }
            return arrayList
        } catch (e: Exception) {
            Log.e("json", e.toString())
        }
        return null
    }

    fun getWeather(location: Location, callBack: FinishCallBack) {
        Log.d("code", location.code)
        download.getWeather(location.code, forecast, object : DownLoadManager.CallBack {
            override fun getResult(result: String) {
                getForecastFromJson(result, location)
                callBack.onFinish(true)
            }
        })
        download.getWeather(location.code, current, object : DownLoadManager.CallBack {
            override fun getResult(result: String) {
                getCurentFromJson(result, location)
                callBack.onFinish(current = true)
            }
        })
    }

    private fun getForecastFromJson(json: String, location: Location) {
        try {
            val all = JSONObject(json).get("forecasts") as JSONArray
            val city = all.get(0) as JSONObject
            val casts = city.get("casts") as JSONArray
            val weather = casts.get(0) as JSONObject
            location.low = weather.getString("nighttemp")
            Log.d("low", location.low)
            location.high = weather.getString("daytemp")
            Log.d("high", location.high)
            Log.d("location getForecastFromJson", location.toString())
        } catch (e: Exception) {
            Log.e("json", e.toString())
        }
    }

    private fun getCurentFromJson(json: String, location: Location) {
        try {
            val all = JSONObject(json).get("lives") as JSONArray
            val lives = all.get(0) as JSONObject
            location.dayWeather = lives.getString("weather")
            Log.d("weather", location.dayWeather)
            location.current = lives.getString("temperature")
            Log.d("temperature", location.current)
        } catch (e: Exception) {
            Log.e("json", e.toString())
        }
    }
}