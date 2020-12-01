package com.example.demomvp.data.source.remote

interface OnFetchDataSonListener<T> {
    fun onSuccess(data: T)
    fun onError(exception: Exception?)
}
