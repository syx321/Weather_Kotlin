package com.example.weather.Controller

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.weather.R
import com.example.weather.model.DownLoadHandler
import com.example.weather.Controller.SYXAdapter.Type.*
import com.example.weather.model.Location
import java.io.BufferedWriter
import java.io.OutputStreamWriter

class AddCity : BaseViewController() {
    lateinit var city: String
//    val editor = getSharedPreferences("weather", Context.MODE_PRIVATE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        city = intent.getStringExtra("province") as String
        setupUI()
    }

    override fun onStart() {
        super.onStart()
        Log.d("city", city)
        download(city)
        val context = this
        adapter.setOnClickLitener(object : SYXAdapter.OnItemClickLitener {
            override fun onItemClicked(position: Int) {
                Log.d("click", position.toString())
                val location = dataSource[position]
                Log.d("city", location.city)
//                val intent = Intent(context, MainActivity::class.java)
//                intent.putExtra("city", location.city)
//                startActivity(intent)
            }
        })
    }

    override fun setupUI() {
        setContentView(R.layout.activity_add_city)
        downLoadHandler = DownLoadHandler.share
        dataSource = ArrayList()
        listView = findViewById(R.id.all_city_list)
        adapter = SYXAdapter(this, R.layout.list_item, dataSource, SELECTABLE)
        listView.adapter = adapter
    }
}