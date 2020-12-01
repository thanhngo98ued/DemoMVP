package com.example.demomvp.screen.main.adapter

import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demomvp.R
import com.example.demomvp.data.model.Weather
import com.example.demomvp.utils.BitmapLoad
import com.example.demomvp.utils.Constant
import com.example.demomvp.utils.OnItemRecyclerViewClickListener
import kotlinx.android.synthetic.main.customrecyclerview.view.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder?>() {

    private val weathers = mutableListOf<Weather>()
    private var onItemClickListener: OnItemRecyclerViewClickListener<Weather>? = null

    fun updateData(weathers: MutableList<Weather>?) {
        weathers?.let {
            this.weathers.clear()
            this.weathers.addAll(weathers)
            notifyDataSetChanged()
        }
    }

    fun registerItemRecyclerViewClickListener(
        onItemRecyclerViewClickListener: OnItemRecyclerViewClickListener<Weather>?
    ) {
        onItemClickListener = onItemRecyclerViewClickListener
    }

    inner class ViewHolder(
        itemView: View,
        private val itemListener: OnItemRecyclerViewClickListener<Weather>?
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private var listener: OnItemRecyclerViewClickListener<Weather>? = null

        fun binViewData(weather: Weather) {
            itemView.textViewStatus.text = weather.timeWeather
            itemView.textViewTempMax.text = weather.tempMax
            itemView.textViewTempMin.text = weather.tempMin
            itemView.setOnClickListener(this)
            BitmapLoad(itemView.imgWeather).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Constant.IMAGE_URL+weather.imageUrl+Constant.TYPE_IMAGE)
            listener = itemListener
        }

        override fun onClick(v: View?) {
            listener?.onItemClickListener(weathers[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.customrecyclerview, parent, false)
        return ViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binViewData(weathers[position])
    }

    override fun getItemCount(): Int = weathers.size
}
