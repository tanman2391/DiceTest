package com.example.diceroll

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModelProvider
import com.example.diceroll.differentDice.DiceRollViewModel
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private val viewModel: DiceRollViewModel by lazy { ViewModelProvider(this)[DiceRollViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.rollDiceButton)
        val result = findViewById<TextView>(R.id.rollResultTextView)

        val editText = findViewById<EditText>(R.id.et_user_input)
        val textInputLayout = findViewById<TextInputLayout>(R.id.til_user_input)

        button.setOnClickListener {

            // Extract the input from editText
            val inputValue = "${editText.text}"

            // Validate the input for edge cases
            val sidesNum = when {
                inputValue.isBlank() -> {
                    textInputLayout.error = getString(R.string.input_error_message)
                    // If input is blank, show error and exit
                    return@setOnClickListener
                }
                inputValue.isDigitsOnly().not() -> {
                    textInputLayout.error = getString(R.string.invalid_input_hint)
                    // If input is not a numerical value, show error and exit
                    return@setOnClickListener
                }
                else -> {
                    textInputLayout.error = null
                    inputValue.toInt()
                }
            }

            // Invoke the roll function with sidesNum
            viewModel.rollDie(sidesNum)
        }

        // Observe data coming in from viewModel's livedata for updated values
        viewModel.dieFaceLiveData.observe(this) {
            it?.let {
                val resultText = getString(R.string.result_template) + it
                result.text = resultText
            }
        }
    }
}
