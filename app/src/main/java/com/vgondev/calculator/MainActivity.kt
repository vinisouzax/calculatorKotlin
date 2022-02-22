package com.vgondev.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.*

class MainActivity : AppCompatActivity() {
    private val LOG_TAG = "Calculator";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btCalculate: Button = findViewById(R.id.bt_calculate)

        btCalculate.setOnClickListener {
            if(isFieldsEmpty()) {
                val calculation = calculate()
                val tvCalculation: TextView = findViewById(R.id.tv_calculation)
                tvCalculation.text = calculation.toString()
            }
        }
    }

    private fun isFieldsEmpty(): Boolean {
        val etFirstValue: EditText = findViewById(R.id.et_first_value)
        val etSecondValue: EditText = findViewById(R.id.et_second_value)
        var isValid = true;

        if (TextUtils.isEmpty(etFirstValue.text.toString())) {
            etFirstValue.error = getString(R.string.empty_value_error)
            isValid = false
        }
        if (TextUtils.isEmpty(etSecondValue.text.toString())) {
            etSecondValue.error = getString(R.string.empty_value_error)
            isValid = false
        }

        return isValid
    }

    private fun calculate(): Double {
        val rgOperator: RadioGroup = findViewById(R.id.rg_operator)
        val checked = rgOperator.checkedRadioButtonId
        val etFirstValue: EditText = findViewById(R.id.et_first_value)
        val etSecondValue: EditText = findViewById(R.id.et_second_value)
        val firstValue = etFirstValue.text.toString().toDouble()
        val secondValue = etSecondValue.text.toString().toDouble()

        Log.d(LOG_TAG, "Checked -> $checked")
        Log.d(LOG_TAG, "firstValue -> $firstValue")
        Log.d(LOG_TAG, "secondValue -> $secondValue")

        val calculation = when (checked) {
            R.id.rb_sum -> firstValue + secondValue
            R.id.rb_subtraction -> firstValue - secondValue
            R.id.rb_multiplication -> firstValue * secondValue
            else -> firstValue / secondValue
        }

        return calculation
    }
}