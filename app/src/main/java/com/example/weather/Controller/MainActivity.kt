package com.example.weather.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import com.example.weather.R
import com.example.weather.model.DownLoadHandler
import com.example.weather.model.Location
import kotlin.collections.ArrayList

class MainActivity : BaseViewController() {
    lateinit var add: TextView
    lateinit var subscribed: ListView
    lateinit var adapter: SYXAdapter
    var dataSource: ArrayList<Location> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribed = findViewById(R.id.subscribed_List)
        adapter = SYXAdapter(this,R.layout.list_item,dataSource)
        subscribed.adapter = adapter
        add = findViewById(R.id.add)
        add.setOnClickListener {
            val intent = Intent(this,addProvince::class.java)
            startActivity(intent)
        }
    }
}