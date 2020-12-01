package com.example.demomvp.data.source

import com.example.demomvp.data.model.Weather
import com.example.demomvp.data.source.remote.OnFetchDataSonListener

interface WeatherDataSource {

    interface Local

    interface Remote {
        fun getWeather(listener: OnFetchDataSonListener<MutableList<Weather>>)
    }
}
