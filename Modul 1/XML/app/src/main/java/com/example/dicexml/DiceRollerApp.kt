package com.example.dicexml

import com.example.dicexml.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

fun diceRollerApp(binding: ActivityMainBinding) {
    val result1 = randomDice()
    val result2 = randomDice()

    val imageResource1 = getDiceImage(result1)
    val imageResource2 = getDiceImage(result2)

    binding.imageView.setImageResource(imageResource1)
    binding.imageView2.setImageResource(imageResource2)

    if (result1 == result2) {
        Snackbar.make(binding.button, "Selamat, anda dapat dadu double", Snackbar.LENGTH_LONG).show()
    }
    else if (result1 != result2) {
        Snackbar.make(binding.button, "Anda belum beruntung!", Snackbar.LENGTH_LONG).show()
    }
}

fun randomDice() : Int {
    return (1..6).random()
}

fun getDiceImage(result: Int) : Int {
    return when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
}