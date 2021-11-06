package com.example.weather.Controller

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.example.weather.R
import com.example.weather.model.DownLoadHandler
import kotlin.collections.ArrayList

class MainActivity : BaseViewController() {
    lateinit var add: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
    }

    override fun setupUI() {
        super.setupUI()
        setContentView(R.layout.activity_main)
        downLoadHandler = DownLoadHandler.share
        dataSource = ArrayList()
        listView = findViewById(R.id.subscribed_List)
        adapter = SYXAdapter(this, R.layout.list_item, dataSource)
        listView.adapter = adapter
        add = findViewById(R.id.add)
        add.setOnClickListener {
            val intent = Intent(this, addProvince::class.java)
            startActivity(intent)
        }
    }
}