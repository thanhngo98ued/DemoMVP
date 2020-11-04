package com.example.demomvp.screen.main

import com.example.demomvp.data.model.Weather
import com.example.demomvp.utils.BasePresenter

interface MainContract {
    interface View {
        fun onGetWeatherSuccess(weather: MutableList<Weather>)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getWeather()
    }
}
