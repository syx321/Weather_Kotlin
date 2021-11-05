package com.example.weather.model

import com.example.weather.Controller.SYXAdapter
import com.example.weather.Controller.SYXAdapter.Type.*

class Location(
    val city: String,
    val id: String,
    val type: SYXAdapter.Type = SELECTABLE,
    var high: String = "0",
    var low: String = "0",
    var current: String = "0"
)