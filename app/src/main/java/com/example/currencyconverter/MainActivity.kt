package com.example.currencyconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener

private const val TAG = "MainActivity"

private const val YEN2USD = 153.17

class MainActivity : AppCompatActivity() {

    private lateinit var etCurrency1Amount: EditText
    private lateinit var tvCurrency2Result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etCurrency1Amount = findViewById<EditText>(R.id.etCurrency1Amount)
        tvCurrency2Result = findViewById<TextView>(R.id.tvCurrency2Result)

        etCurrency1Amount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG, "afterTextChanged $s")
                computeConvserion()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int
            ) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int
            ) {}

        })

    }

    private fun computeConvserion() {
        if (etCurrency1Amount.text.isEmpty()) {
            tvCurrency2Result.text = ""
            return
        }
        val currency1Amount = etCurrency1Amount.text.toString().toDouble()
        val result = currency1Amount / YEN2USD
        tvCurrency2Result.text = "%.2f".format(result)
    }
}