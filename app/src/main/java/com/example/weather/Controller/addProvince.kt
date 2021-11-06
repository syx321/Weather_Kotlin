package com.example.weather.Controller

import android.os.Bundle
import com.example.weather.R
import com.example.weather.model.DownLoadHandler
import com.example.weather.model.DownLoadHandler.FinishCallBack
import com.example.weather.model.Location
import com.example.weather.Controller.SYXAdapter.Type.*

class addProvince : BaseViewController() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setupUI()
    }

    override fun onStart() {
        super.onStart()
        downLoadHandler.getLocation(object : DownLoadHandler.CityCallBack {
            override fun getResult(result: ArrayList<Location>) {
                dataSource += result
                runOnUiThread {
                    reloadList()
                    for (i in 0 until dataSource.count()) {
                        downLoadHandler.getWeather(dataSource[i], object : FinishCallBack {
                            override fun onFinish(forecast: Boolean, current: Boolean) {
                                if (forecast && current) {
                                    reloadCell(i)
                                }
                            }
                        })
                    }
                }
            }
        })
    }

    override fun setupUI() {
        setContentView(R.layout.activity_add_province)
        downLoadHandler = DownLoadHandler.share
        dataSource = ArrayList()
        listView = findViewById(R.id.all_province_list)
        adapter = SYXAdapter(this, R.layout.list_item, dataSource, UNSELECTABLE)
        listView.adapter = adapter
    }
}