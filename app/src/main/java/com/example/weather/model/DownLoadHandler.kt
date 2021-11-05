package com.example.weather.model

import android.util.Log
import com.example.weather.Util.DownLoadManager
import org.json.JSONArray
import org.json.JSONObject

class DownLoadHandler {
    companion object {
        val share = DownLoadHandler()
    }

    interface CallBack {
        fun getResult(result: ArrayList<Location>)
    }

    fun getAllCity(callBack: CallBack) {
        val download = DownLoadManager.share
        download.getCity("中华人民共和国", object : DownLoadManager.CallBack {
            override fun getResult(result: String) {
//                Log.d("responseData", result)
                getCityNameFromJson(result)?.let { callBack.getResult(it) }
            }
        })
    }

    private fun getCityNameFromJson(json: String): ArrayList<Location>? {
        try {
            val arrayList = ArrayList<Location>()
            val all = JSONObject(json).get("districts") as JSONArray
            val city = all.get(0 ) as JSONObject
            val provinces = city.get("districts") as JSONArray
            for (i in 1 until provinces.length()) {
                val jsonObject = provinces.getJSONObject(i)
                val cityName = jsonObject.getString("name")//name
                val id = jsonObject.getString("adcode")
                arrayList.add(Location(cityName,id))
            }
            return arrayList
        } catch (e: Exception) {
            Log.e("json", e.toString())
        }
        return null
    }
}