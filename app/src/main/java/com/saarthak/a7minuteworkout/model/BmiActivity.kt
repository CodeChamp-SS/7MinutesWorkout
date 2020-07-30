package com.saarthak.a7minuteworkout.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.saarthak.a7minuteworkout.R
import kotlinx.android.synthetic.main.activity_bmi.*
import java.math.BigDecimal
import java.math.RoundingMode

class BmiActivity : AppCompatActivity() {

    var ht : Float = 0f
    var wt : Float = 0f

    private fun validate_m() : Boolean = !(ht_tv.text!!.isEmpty() or wt_tv.text!!.isEmpty())

    private fun validate_u() : Boolean = !(ht_in.text!!.isEmpty() or ht_ft.text!!.isEmpty() or wt_tv_p.text!!.isEmpty())

    private fun showBmi(bmi : Float){
        bmi_tv.text = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        when(bmi){
            in 0f..16f -> status_tv.text = "Result : Severe Thinnes"
            in 16f..17f -> status_tv.text = "Result : Moderate Thinnes"
            in 17f..18.5f -> status_tv.text = "Result : Mild Thinnes"
            in 18.5f..25f -> status_tv.text = "Result : Normal"
            in 25f..30f -> status_tv.text = "Result : Overweight"
            else -> status_tv.text = "Result : Obese"
        }
    }

    private fun bmi_metric(ht : Float, wt : Float) : Float = wt / (ht*ht)

    private fun bmi_us(ht : Float, wt : Float) : Float = 703f * (wt / (ht*ht))

    private fun reset(){
        ht_tv.setText("")
        ht_ft.setText("")
        ht_in.setText("")

        wt_tv.setText("")
        wt_tv_p.setText("")

        bmi_tv.setText("")
        status_tv.setText("")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        setSupportActionBar(b_toolbar)
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        b_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        r_grp.setOnCheckedChangeListener { group, checkedId ->
            reset()

            if(checkedId == R.id.us_u){
                ll_metric.visibility = View.INVISIBLE
                ll_us.visibility = View.VISIBLE
            }
            else{
                ll_metric.visibility = View.VISIBLE
                ll_us.visibility = View.INVISIBLE
            }
        }

        calcB.setOnClickListener {

            var bmi = 0f

            if(us_u.isChecked) {

                if(validate_u()) {
                    ht = 12f * ht_ft.text.toString().toFloat() + ht_in.text.toString().toFloat()
                    wt = wt_tv_p.text.toString().toFloat()

                    bmi = bmi_us(ht, wt)
                    showBmi(bmi)
                }
                else Toast.makeText(this, "Weight and Height are required !", Toast.LENGTH_SHORT).show()
            }
            else {

                if(validate_m()) {
                    ht = ht_tv.text.toString().toFloat()
                    ht /= 100F
                    wt = wt_tv.text.toString().toFloat()

                    bmi = bmi_metric(ht, wt)
                    showBmi(bmi)
                }
                else Toast.makeText(this, "Weight and Height are required !", Toast.LENGTH_SHORT).show()
            }

        }

    }
}