package com.saarthak.a7minuteworkout.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.saarthak.a7minuteworkout.R
import com.saarthak.a7minuteworkout.data.DataBaseHandler
import com.saarthak.a7minuteworkout.ui.RecyclerViewAdapter3
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {

    var dbHandler : DataBaseHandler? = null
    var dates : ArrayList<String>? = null

    var adapter : RecyclerViewAdapter3? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setSupportActionBar(hist_toolbar)

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        hist_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        dbHandler = DataBaseHandler(this)
        dates = dbHandler!!.getItems()

        adapter = RecyclerViewAdapter3(this, dates!!)

        val rv = hist_rv
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)

        rv.adapter = adapter
    }
}