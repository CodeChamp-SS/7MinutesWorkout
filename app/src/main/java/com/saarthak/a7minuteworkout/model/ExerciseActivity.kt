package com.saarthak.a7minuteworkout.model

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.saarthak.a7minuteworkout.R
import com.saarthak.a7minuteworkout.data.Constants
import com.saarthak.a7minuteworkout.ui.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.conf_dialog.*
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener, Serializable {

    private var countDownTimer : CountDownTimer? = null
    private val duration_rest : Long = 15000
    private val duration_exercise : Long = 30000

    private var exercises : ArrayList<Exercises>? = null
    private var defaultEx : ArrayList<Exercises>? = null
    private var currPosn = -1

    private var t2s : TextToSpeech? = null

    private var player : MediaPlayer? = null

    private var adapter : RecyclerViewAdapter? = null

    private fun setRestProgressBar(){

        progressBar.max = 15
        progressBar.setProgress(0)

        countDownTimer = object : CountDownTimer(duration_rest, 1000){
            override fun onFinish() {
//                Toast.makeText(this@ExerciseActivity, "Exercise starts", Toast.LENGTH_SHORT).show()

                ll_exercise.visibility = View.VISIBLE
                ll_rest.visibility = View.INVISIBLE

                player!!.pause()

                defaultEx!![currPosn].isSelected = true
                adapter!!.notifyDataSetChanged()

                setUpExerciseView()
            }

            override fun onTick(millisUntilFinished: Long) {

                var timeLeft = millisUntilFinished/1000

                if(timeLeft < 4) player!!.start()

                progressBar.progress = (timeLeft.toInt())

                timer_tv.text = timeLeft.toString()
            }

        }.start()

    }

    private fun setUpRestView(){
        cancel()

        ++currPosn
        upcoming_ex.text = defaultEx!![currPosn].name

        setRestProgressBar()
    }

    private fun setExercisesProgressBar(){

        progressBar2.max = 30
        progressBar2.progress = 0

        countDownTimer = object : CountDownTimer(duration_exercise, 1000){
            override fun onFinish() {

                if(currPosn < defaultEx!!.size-1){
                    ll_exercise.visibility = View.INVISIBLE
                    ll_rest.visibility = View.VISIBLE

                    defaultEx!![currPosn].isSelected = false
                    defaultEx!![currPosn].isCompleted = true
                    adapter!!.notifyDataSetChanged()

                    setUpRestView()
                }
                else {
                    startActivity(Intent(this@ExerciseActivity, FinishActivity::class.java))
                    finish()
                }

                player!!.pause()
            }

            override fun onTick(millisUntilFinished: Long) {
                var timeLeft = millisUntilFinished/1000

                if(timeLeft < 4) player!!.start()

                progressBar2.progress = (timeLeft.toInt())

                timer_tv2.text = timeLeft.toString()
            }

        }.start()

    }

    private fun setUpExerciseView(){
        cancel()

        val exercise = defaultEx!![currPosn]

        exercise_iv.setImageResource(exercise.img)
        name_tv.text = exercise.name
        speak(exercise.name)

        setExercisesProgressBar()
    }

    private fun cancel(){

        if(countDownTimer != null){
            countDownTimer!!.cancel()
            countDownTimer = null
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        cancel()

        if(t2s != null){
            t2s!!.stop()
            t2s!!.shutdown()
        }

        if(player != null){
            player!!.stop()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        if(hasFocus){
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)


        setSupportActionBar(ex_toolbar)

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        ex_toolbar.setNavigationOnClickListener{
            showConfDialog()
        }

        player = MediaPlayer.create(applicationContext, R.raw.clok_tick)
//        player!!.isLooping = false

        t2s = TextToSpeech(this, this)


        exercises = Constants.exerciseList()

        defaultEx = intent.getSerializableExtra("ex") as ArrayList<Exercises>?

        if(defaultEx == null || defaultEx!!.isEmpty()){
            defaultEx = ArrayList()

            for(x in 0..9)
                defaultEx!!.add(exercises!![x])
        }

        ex_no_rv.setHasFixedSize(true)
        ex_no_rv.layoutManager =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter = RecyclerViewAdapter(this, defaultEx!!)
        ex_no_rv.adapter = adapter
        adapter!!.notifyDataSetChanged()

        setUpRestView()

    }

    private fun showConfDialog(){

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.conf_dialog)

        dialog.yesB.setOnClickListener {
            finish()

            dialog.dismiss()
        }
        dialog.noB.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun speak(text : String){

        t2s!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")

    }

    override fun onInit(status: Int) {

        if(status == TextToSpeech.SUCCESS){

            val res =t2s!!.setLanguage(Locale.UK)

            if(res == TextToSpeech.LANG_NOT_SUPPORTED || res == TextToSpeech.LANG_MISSING_DATA)
                Log.d("t2s", "lang not supported")

        }else Log.d("t2s", "failed !")

    }
}