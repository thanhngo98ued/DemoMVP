package com.example.demomvp.data.source.local

import com.example.demomvp.data.source.WeatherDataSource

class WeatherLocalDataSource : WeatherDataSource.Local {

    private object Holder {
        val INSTANCE = WeatherLocalDataSource()
    }

    companion object {
        val instance: WeatherLocalDataSource by lazy { Holder.INSTANCE }
    }
}
