package com.example.weather.Controller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.weather.R
import com.example.weather.model.DownLoadHandler
import com.example.weather.model.DownLoadHandler.FinishCallBack
import com.example.weather.model.Location
import com.example.weather.Controller.SYXAdapter.Type.*

class AddProvince : BaseViewController() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
    }

    override fun onStart() {
        super.onStart()
        download("中国")
        val context = this
        adapter.setOnClickLitener(object: SYXAdapter.OnItemClickLitener{
            override fun onItemClicked(position: Int) {
                Log.d("click",position.toString())
                val location = dataSource[position]
                Log.d("province",location.city)
                val intent = Intent(context,AddCity::class.java)
                intent.putExtra("province",location.city)
                startActivity(intent)
            }
        })
    }

    override fun setupUI() {
        setContentView(R.layout.activity_add_province)
        downLoadHandler = DownLoadHandler.share
        dataSource = ArrayList()
        listView = findViewById(R.id.all_province_list)
        adapter = SYXAdapter(this, R.layout.list_item, dataSource, SELECTABLE)
        listView.adapter = adapter
    }
}