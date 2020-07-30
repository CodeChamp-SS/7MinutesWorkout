package com.saarthak.a7minuteworkout.model

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import com.saarthak.a7minuteworkout.R
import com.saarthak.a7minuteworkout.data.Constants
import com.saarthak.a7minuteworkout.ui.MyLookup
import com.saarthak.a7minuteworkout.ui.RecyclerViewAdapter2
import kotlinx.android.synthetic.main.activity_customize.*
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.conf_dialog.*
import java.io.Serializable

class CustomizeActivity : AppCompatActivity(), Serializable {

    private var adapter : RecyclerViewAdapter2? = null

    private var tracker : SelectionTracker<Long>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customize)

        setSupportActionBar(c_toolbar)

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        c_toolbar.setNavigationOnClickListener{
            onBackPressed()
        }

        if(savedInstanceState != null) tracker?.onRestoreInstanceState(savedInstanceState)

        allEx_rv.setHasFixedSize(true)
        allEx_rv.layoutManager = LinearLayoutManager(this)

        adapter = RecyclerViewAdapter2(this, Constants.exerciseList())
        allEx_rv.adapter = adapter

        tracker = SelectionTracker.Builder<Long>("selection-1", allEx_rv,
            StableIdKeyProvider(allEx_rv), MyLookup(allEx_rv),
            StorageStrategy.createLongStorage()).withSelectionPredicate(SelectionPredicates.createSelectAnything()).build()

        tracker!!.addObserver(object : SelectionTracker.SelectionObserver<Long>() {
            override fun onSelectionChanged() {
                val noOfItems = tracker!!.selection.size()

                if(noOfItems >= 0){
                    noItem_tv.text = "$noOfItems items selected"
                }

            }
        })

        adapter!!.setTracker(tracker)

        adapter!!.notifyDataSetChanged()

        startB_c.setOnClickListener {
            val ex: ArrayList<Exercises> = adapter!!.getSelectedEx()

//            ex.forEach {
//                Log.d("ex : ", it.name + "\n")
//            }

            if(ex.size != 10) Toast.makeText(this, "Please select 10 items", Toast.LENGTH_SHORT).show()

            else{

                // TODO : inorder to send arrayList<Exercises> via intent the class Exercises must also extend "Serializable"

                val intent = Intent(this, ExerciseActivity::class.java)
                intent.putExtra("ex", ex)

                startActivity(intent)
                finish()
            }

        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        tracker!!.onSaveInstanceState(outState)
    }
}