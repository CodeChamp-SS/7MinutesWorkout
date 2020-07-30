package com.saarthak.a7minuteworkout.ui

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView

class MyLookup (val rv : RecyclerView) : ItemDetailsLookup<Long>() {
    override fun getItemDetails(e: MotionEvent): ItemDetails<Long>? {

        val view = rv.findChildViewUnder(e.x, e.y)

        return (rv.getChildViewHolder(view!!) as RecyclerViewAdapter2.ViewHolder).getItemDetails()
    }
}