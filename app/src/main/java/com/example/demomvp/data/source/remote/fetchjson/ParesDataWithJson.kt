package com.example.demomvp.data.source.remote.fetchjson

import com.example.demomvp.data.model.WeatherEntry
import com.example.demomvp.utils.Constant
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ParesDataWithJson {

    fun getJsonFromUrl(urlString: String?): String {
        val url = URL(urlString)
        val httpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.connectTimeout = TIME_OUT
        httpURLConnection.readTimeout = TIME_OUT
        httpURLConnection.requestMethod = METHOD_GET
        httpURLConnection.doOutput = true
        httpURLConnection.connect()
        val bufferedReader = BufferedReader(InputStreamReader(url.openStream()))
        val stringBuilder = StringBuilder()
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
        bufferedReader.close()
        httpURLConnection.disconnect()
        return stringBuilder.toString()
    }

    fun parseJsonToDaTa(jsonObject: JSONObject?, keyEntity: String): Any {
        val data = mutableListOf<Any>()
        try {
            val jsonArray = jsonObject?.getJSONArray(WeatherEntry.LIST)
            for (i in 0 until (jsonArray?.length() ?: 0)) {
                val jsonObjects = jsonArray?.getJSONObject(i)
                val item = pareJsonToObject(jsonObjects, keyEntity)
                item?.let { data.add(item) }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return data
    }

    private fun pareJsonToObject(jsonObject: JSONObject?, keyEntity: String): Any? {
        try {
            jsonObject?.let {
                when (keyEntity) {
                    Constant.WEATHER_MODEL -> return ParseJson().weatherParseJson(it)
                    else -> null
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    companion object {
        private const val TIME_OUT = 20000
        private const val METHOD_GET = "GET"
    }
}
