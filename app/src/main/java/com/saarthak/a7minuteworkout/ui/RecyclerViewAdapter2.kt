package com.saarthak.a7minuteworkout.ui

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.saarthak.a7minuteworkout.R
import com.saarthak.a7minuteworkout.model.Exercises
import kotlinx.android.synthetic.main.all_ex_list.view.*

class RecyclerViewAdapter2 (val context: Context, val items : ArrayList<Exercises>) : RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder>() {

    private var tracker : SelectionTracker<Long>? = null

    private var selectedExercises : ArrayList<Exercises>? = null
    private var isPresent : ArrayList<Boolean>? = null

    init {
        setHasStableIds(true)
        selectedExercises = ArrayList()

        isPresent = ArrayList()
//
        for(i in 0 until items.size) isPresent!!.add(false)
    }

    fun setTracker(tracker: SelectionTracker<Long>?){
        this.tracker = tracker
    }

    fun getSelectedEx() : ArrayList<Exercises>{

        selectedExercises!!.clear()

        for(i in 0 until isPresent!!.size){
            if(isPresent!![i]) selectedExercises!!.add(items[i])
        }

        return selectedExercises!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.all_ex_list, parent, false))
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ex = items[position]

        holder.exImg.setImageResource(ex.img)
        holder.exName.text = ex.name

        val par = holder.exName.parent as LinearLayout

        if(tracker!!.isSelected(position.toLong())) {
            par.setBackgroundResource(R.drawable.cv_bg_2)

            isPresent!![position] = true
        }
        else {
            par.setBackgroundResource(R.drawable.cv_bg)

            isPresent!![position] = false
        }
    }

    class ViewHolder(val view : View) : RecyclerView.ViewHolder(view) {

        val exImg = view.ex_gif
        val exName = view.ex_name

        fun getItemDetails() : ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>(){
                override fun getSelectionKey(): Long? = itemId

                override fun getPosition(): Int = adapterPosition

            }

    }
}