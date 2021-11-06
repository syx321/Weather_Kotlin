package com.example.weather.Controller

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.weather.R
import com.example.weather.model.Location
import java.util.*


class SYXAdapter(
    activity: Activity,
    val resource: Int,
    var data: ArrayList<Location>,
    val type: Type = Type.SELECTABLE
) : ArrayAdapter<Location>(activity, resource, data) {
    inner class ViewHolder(
        val type: Type = Type.SELECTABLE,
        val location: TextView,
        val dayWeather: TextView,
        val high: TextView,
        val low: TextView,
        val current: TextView
    )

    enum class Type {
        SELECTABLE,
        UNSELECTABLE
    }

    lateinit var myLitener: OnItemClickLitener

    @SuppressLint("ViewHolder", "SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resource, parent, false)
            val location = view.findViewById<TextView>(R.id.location)
            val dayWeather = view.findViewById<TextView>(R.id.dayweather)
            val high = view.findViewById<TextView>(R.id.high)
            val low = view.findViewById<TextView>(R.id.low)
            val current = view.findViewById<TextView>(R.id.current)
            viewHolder = ViewHolder(type, location, dayWeather, high, low, current)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        this.data.get(position).let {
            viewHolder.location.text = it.city + "°"
            viewHolder.high.text = it.high + "°"
            viewHolder.low.text = it.low + "°"
            viewHolder.current.text = it.current + "°"
            viewHolder.dayWeather.text = it.dayWeather
        }

        when (type) {
            Type.SELECTABLE -> {
                view.isEnabled = true
                view.setOnClickListener(View.OnClickListener {
                    myLitener.onItemClicked(position)
                })
            }
            Type.UNSELECTABLE -> view.isEnabled = false
        }
        return view
    }

    override fun isEnabled(position: Int): Boolean {
        return false
    }

    fun setOnClickLitener(mOnItemClickLitener: OnItemClickLitener) {
        this.myLitener = mOnItemClickLitener
    }

    interface OnItemClickLitener {
        fun onItemClicked(position: Int)
    }
}