package com.example.demomvp.screen.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demomvp.R
import com.example.demomvp.data.model.Weather
import com.example.demomvp.data.source.WeatherRepository
import com.example.demomvp.screen.main.adapter.MainAdapter
import com.example.demomvp.utils.Constant
import com.example.demomvp.utils.OnItemRecyclerViewClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View,
    OnItemRecyclerViewClickListener<Weather> {

    private val adapter: MainAdapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
    }

    private fun initView() {
        recyclerWeather.apply {
            setHasFixedSize(true)
            adapter = this@MainActivity.adapter
            layoutManager =
                LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
        }
        adapter.registerItemRecyclerViewClickListener(this)
    }

    private fun initData() {
        MainPresenter(WeatherRepository.INSTANCE).apply {
            setView(this@MainActivity)
            onStart()
        }
    }

    override fun onGetWeatherSuccess(weather: MutableList<Weather>) {
        adapter.updateData(weather)
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(this, exception?.message ?: Constant.TYPE_NULL, Toast.LENGTH_SHORT).show()
        Log.d(Constant.LOG_D, exception?.message ?: Constant.TYPE_NULL)
    }

    override fun onItemClickListener(item: Weather?) {
        Toast.makeText(this, item.toString(), Toast.LENGTH_SHORT).show()
    }
}
