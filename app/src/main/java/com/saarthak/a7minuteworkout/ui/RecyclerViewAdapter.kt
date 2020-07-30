package com.saarthak.a7minuteworkout.ui

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.saarthak.a7minuteworkout.R
import com.saarthak.a7minuteworkout.model.Exercises
import kotlinx.android.synthetic.main.ex_no_list.view.*

class RecyclerViewAdapter (val context: Context, val items : ArrayList<Exercises>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.ex_no_list, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ex : Exercises = items[position]

        holder.exNo.text = (position+1).toString()

        if(ex.isSelected) holder.exNo.background = ContextCompat.getDrawable(context, R.drawable.circular_border_o)

        else if(ex.isCompleted){
            holder.exNo.background = ContextCompat.getDrawable(context, R.drawable.inner_circle)
            holder.exNo.setTextColor(Color.WHITE)
        }

    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        val exNo = view.ex_no_tv

    }
}