package com.example.weather.Controller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseViewController : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
    }
}