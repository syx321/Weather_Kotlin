package com.example.weather.Controller

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
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
    }
}