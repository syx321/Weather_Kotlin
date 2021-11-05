package com.example.weather.Controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weather.R

class addCity : BaseViewController() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_city)
    }
}