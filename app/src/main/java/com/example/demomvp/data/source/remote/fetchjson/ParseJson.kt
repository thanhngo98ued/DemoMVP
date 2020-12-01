package com.example.demomvp.data.source.remote.fetchjson

import com.example.demomvp.data.model.Weather
import com.example.demomvp.data.model.WeatherEntry
import org.json.JSONObject

class ParseJson {
    fun weatherParseJson(jsonObject: JSONObject): Weather {
        val main = jsonObject.getJSONObject(WeatherEntry.MAIN)
        val temMax = main.getString(WeatherEntry.TEMP_MAX)
        val temMin = main.getString(WeatherEntry.TEMP_MIN)
        val weatherArray = jsonObject.getJSONArray(WeatherEntry.WEATHER)
        val weatherObject = weatherArray.getJSONObject(0)
        val status = weatherObject.getString(WeatherEntry.STATUS)
        val imageMain = weatherObject.getString(WeatherEntry.ICON)
        return Weather(imageMain, temMax, temMin, status)
    }
}
