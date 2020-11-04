package com.example.demomvp.data.source

import com.example.demomvp.data.model.Weather
import com.example.demomvp.data.source.local.WeatherLocalDataSource
import com.example.demomvp.data.source.remote.OnFetchDataSonListener
import com.example.demomvp.data.source.remote.WeatherRemoteDataSource

class WeatherRepository private constructor(
    private val remote: WeatherDataSource.Remote,
    private val local: WeatherDataSource.Local
) {

    private object Holder {
        val INSTANCE = WeatherRepository(
            remote = WeatherRemoteDataSource.instance,
            local = WeatherLocalDataSource.instance
        )
    }

    fun getWeather(listener: OnFetchDataSonListener<MutableList<Weather>>) {
        remote.getWeather(listener)
    }

    companion object {
        val INSTANCE: WeatherRepository by lazy { Holder.INSTANCE }
    }
}
