package com.example.demomvp.screen.main

import com.example.demomvp.data.model.Weather
import com.example.demomvp.data.source.WeatherRepository
import com.example.demomvp.data.source.remote.OnFetchDataSonListener
import com.example.demomvp.screen.main.MainContract.Presenter

class MainPresenter internal constructor(private val repository: WeatherRepository?) : Presenter {

    private var view: MainContract.View? = null

    override fun getWeather() {
        repository?.getWeather(object : OnFetchDataSonListener<MutableList<Weather>> {
            override fun onSuccess(data: MutableList<Weather>) {
                view?.onGetWeatherSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view?.onError(exception)
            }
        })
    }

    override fun onStart() {
        getWeather()
    }

    override fun onStop(){}

    override fun setView(view: MainContract.View?) {
        this.view = view
    }
}
