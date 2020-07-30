package com.saarthak.a7minuteworkout.ui

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saarthak.a7minuteworkout.R
import kotlinx.android.synthetic.main.history_row.view.*

class RecyclerViewAdapter3(val context: Context, val dates : ArrayList<String>) : RecyclerView.Adapter<RecyclerViewAdapter3.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.history_row, parent, false))
    }

    override fun getItemCount(): Int {
        return dates.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val d = dates[position]

        holder.sNo.text = "${position + 1}."

        var d1 = ""
        var t1 = ""
        for(i in 0 until 10) d1 += d[i]

        for(i in 11 until d.length) t1 += d[i]

        holder.date.text = d1
        holder.time.text = t1

        if(position and 1 == 0){
            holder.hist_row.setBackgroundColor(Color.parseColor("#34DA1356"))
        }
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        val sNo = view.s_no
        val date = view.date_tv
        val time = view.time_tv
        val hist_row = view.hist_row_ll
    }
}