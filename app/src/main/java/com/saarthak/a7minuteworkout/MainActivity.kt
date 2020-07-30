package com.saarthak.a7minuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.saarthak.a7minuteworkout.model.BmiActivity
import com.saarthak.a7minuteworkout.model.CustomizeActivity
import com.saarthak.a7minuteworkout.model.ExerciseActivity
import com.saarthak.a7minuteworkout.model.HistoryActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startB.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }

        customB.setOnClickListener {
            startActivity(Intent(this, CustomizeActivity::class.java))
        }

        bmiB.setOnClickListener {
            startActivity(Intent(this, BmiActivity::class.java))
        }

        historyB.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
    }
}