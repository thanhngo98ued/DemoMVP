package com.example.demomvp.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import java.net.HttpURLConnection
import java.net.URL

@Suppress("UNREACHABLE_CODE")
class BitmapLoad(private val imageView: ImageView) : AsyncTask<String?, Unit, Bitmap?>() {

    override fun doInBackground(vararg params: String?): Bitmap? = try {
        val url = URL(params[0].toString())
        val httpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.connectTimeout = TIME_OUT
        httpURLConnection.readTimeout = TIME_OUT
        httpURLConnection.requestMethod = METHOD_GET
        httpURLConnection.doOutput = true
        httpURLConnection.connect()
        BitmapFactory.decodeStream(url.openStream())

    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    override fun onPostExecute(result: Bitmap?) {
        result?.let { imageView.setImageBitmap(it) }
        super.onPostExecute(result)
    }

    companion object {
        const val TIME_OUT = 20000
        const val METHOD_GET = "GET"
    }
}
