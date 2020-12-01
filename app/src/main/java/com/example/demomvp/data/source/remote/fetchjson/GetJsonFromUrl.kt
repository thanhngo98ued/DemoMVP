package com.example.demomvp.data.source.remote.fetchjson

import android.os.AsyncTask
import com.example.demomvp.data.source.remote.OnFetchDataSonListener
import org.json.JSONObject

@Suppress("UNCHECKED_CAST")
class GetJsonFromUrl<T> constructor(
    private val listener: OnFetchDataSonListener<T>,
    private val keyEntity: String
) : AsyncTask<String?, Void?, String?>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg params: String?): String? {
        var data = ""
        try {
            val paresDataWithJson = ParesDataWithJson()
            data = paresDataWithJson.getJsonFromUrl(params[0].toString())
        } catch (e: Exception) {
            exception = e
        }
        return data
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if (result != null && !result.isBlank()) {
            val jsonObject = JSONObject(result)
            listener.onSuccess(ParesDataWithJson().parseJsonToDaTa(jsonObject, keyEntity) as T)
        } else listener.onError(exception)
    }
}
