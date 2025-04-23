package com.example.calculatortip

import androidx.lifecycle.*
import java.text.NumberFormat
import kotlin.math.ceil

class TipViewModel : ViewModel() {

    val amount = MutableLiveData<String>()
    val tipOption = MutableLiveData<String>()
    val roundUp = MutableLiveData(false)

    val tipResult = MediatorLiveData<String>()

    init {
        val update = { tipResult.value = calculateTip() }
        tipResult.addSource(amount) { update() }
        tipResult.addSource(tipOption) { update() }
        tipResult.addSource(roundUp) { update() }
    }

    private fun calculateTip(): String {
        val amountDouble = amount.value?.toDoubleOrNull() ?: return "Tip Amount: " + NumberFormat.getCurrencyInstance().format(0.0)
        val percentage = when (tipOption.value) {
            "15%" -> 0.15
            "18%" -> 0.18
            "20%" -> 0.20
            else -> 0.15
        }

        var tip = amountDouble * percentage
        if (roundUp.value == true) {
            tip = ceil(tip)
        }

        return "Tip Amount: " + NumberFormat.getCurrencyInstance().format(tip)
    }
}
