package com.saarthak.a7minuteworkout.model

import android.content.Intent
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.saarthak.a7minuteworkout.MainActivity
import com.saarthak.a7minuteworkout.R
import com.saarthak.a7minuteworkout.data.DataBaseHandler
import kotlinx.android.synthetic.main.activity_finish.*
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {

    var dbHandler : DataBaseHandler? = null

    private fun saveHistory(){
        val calendar = Calendar.getInstance()
        val time = calendar.time

//        Log.d("time","" + time)

        val dateFormat = SimpleDateFormat("dd MM yyyy HH:mm:ss", Locale.getDefault())

        val date = dateFormat.format(time)

        dbHandler!!.addDate(date)
//        Log.d("added", "date :  $date added to history")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        dbHandler = DataBaseHandler(this)

        saveHistory()

        finishB.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}