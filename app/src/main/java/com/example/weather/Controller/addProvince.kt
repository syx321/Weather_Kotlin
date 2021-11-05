package com.example.weather.Controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.example.weather.R
import com.example.weather.model.DownLoadHandler
import com.example.weather.model.Location
import com.example.weather.Controller.SYXAdapter.Type.*

class addProvince : BaseViewController() {
    lateinit var allCity: ListView
    lateinit var adapter: SYXAdapter
    var dataSource: ArrayList<Location> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_province)
        allCity = findViewById(R.id.all_province_list)
        adapter = SYXAdapter(this, R.layout.list_item, dataSource, UNSELECTABLE)
        allCity.adapter = adapter

        val downLoadHandler = DownLoadHandler.share

        downLoadHandler.getAllCity(object : DownLoadHandler.CallBack {
            override fun getResult(result: ArrayList<Location>) {
                runOnUiThread {
                    dataSource += result
                    Log.d("data", dataSource.count().toString())
                    adapter.notifyDataSetChanged()
                    allCity.invalidate()
                }
            }
        })
    }
}