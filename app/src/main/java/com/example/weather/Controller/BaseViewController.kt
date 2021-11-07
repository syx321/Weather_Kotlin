package com.example.weather.Controller

import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.weather.R
import com.example.weather.model.DownLoadHandler
import com.example.weather.model.Location

open class BaseViewController : AppCompatActivity() {
    lateinit var listView: ListView
    lateinit var adapter: SYXAdapter
    lateinit var dataSource: ArrayList<Location>
    lateinit var downLoadHandler: DownLoadHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
    }

    open fun setupUI() {}

    protected fun reloadList() {
        adapter.notifyDataSetChanged()
        listView.invalidate()
    }

    protected fun reloadCell(index: Int) {
        val view = listView.getChildAt(index)
        listView.adapter.getView(index, view, listView)
        adapter.notifyDataSetChanged()
    }

    protected fun download(city: String) {
        downLoadHandler.getLocation(city,object : DownLoadHandler.CityCallBack {
            override fun getResult(result: ArrayList<Location>) {
                dataSource += result
                runOnUiThread {
                    reloadList()
                    for (i in 0 until dataSource.count()) {
                        downLoadHandler.getWeather(dataSource[i], object :
                            DownLoadHandler.FinishCallBack {
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
}