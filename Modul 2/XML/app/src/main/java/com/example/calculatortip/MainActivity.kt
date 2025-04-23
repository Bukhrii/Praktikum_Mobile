package com.example.calculatortip

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.calculatortip.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var amount: String = ""
    private var tipOption: String = "15%"
    private var roundUp: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tipOptions = listOf("15%", "18%", "20%")
        val adapter = android.widget.ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, tipOptions)
        binding.autoCompleteTextView.setAdapter(adapter)
        binding.autoCompleteTextView.setText(tipOption, false)

        binding.textInputEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                amount = s.toString()
                updateTip()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.autoCompleteTextView.setOnItemClickListener { parent, _, position, _ ->
            tipOption = parent.getItemAtPosition(position) as String
            updateTip()
        }

        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            roundUp = isChecked
            updateTip()
        }

        updateTip()
    }

    private fun updateTip() {
        val amountDouble = amount.toDoubleOrNull() ?: 0.0
        val tipPercent = when (tipOption) {
            "15%" -> 0.15
            "18%" -> 0.18
            "20%" -> 0.20
            else -> 0.15
        }

        var tip = amountDouble * tipPercent
        if (roundUp) tip = ceil(tip)

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        if (formattedTip == "$0.00") {
            binding.tipAmount.text = "Tip Amount: $0.00"
            binding.tipAmount.textAlignment = View.TEXT_ALIGNMENT_CENTER
        } else {
            binding.tipAmount.text = "Tip Amount:\n$formattedTip"
            binding.tipAmount.textAlignment = View.TEXT_ALIGNMENT_VIEW_START
        }
    }
}
