package com.example.demomvp.data.model

data class Weather(
    val imageUrl: String = "",
    val tempMax: String = "",
    val tempMin: String = "",
    val timeWeather: String = ""
)

object WeatherEntry {
    const val LIST = "list"
    const val TEMP_MAX = "temp_max"
    const val TEMP_MIN = "temp_min"
    const val STATUS = "description"
    const val ICON = "icon"
    const val MAIN = "main"
    const val WEATHER = "weather"
}
