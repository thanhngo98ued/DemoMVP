package com.example.demomvp.data.source.remote

import com.example.demomvp.data.model.Weather
import com.example.demomvp.data.source.WeatherDataSource
import com.example.demomvp.data.source.remote.fetchjson.GetJsonFromUrl
import com.example.demomvp.utils.Constant

class WeatherRemoteDataSource : WeatherDataSource.Remote {

    private var baseUrl = Constant.BASE_URL + Constant.BASE_API_KEY

    private object Holder {
        val INSTANCE = WeatherRemoteDataSource()
    }

    override fun getWeather(listener: OnFetchDataSonListener<MutableList<Weather>>) {
        GetJsonFromUrl(listener, Constant.WEATHER_MODEL).execute(baseUrl)
    }

    companion object {
        val instance: WeatherRemoteDataSource by lazy { Holder.INSTANCE }
    }
}
