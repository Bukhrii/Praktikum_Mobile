package com.example.dicecompose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dicecompose.R

import kotlinx.coroutines.launch

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    var result1 by remember { mutableStateOf(0) }
    var result2 by remember { mutableStateOf(0) }
    val imageResource1 = getDiceImage(result1)
    val imageResource2 = getDiceImage(result2)

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState()}

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
            paddingValues ->
        Column (
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Image(
                    painter = painterResource(imageResource1),
                    contentDescription = result1.toString()
                )
                Image(
                    painter = painterResource(imageResource2),
                    contentDescription = result2.toString()
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = {
                result1 = (1..6).random()
                result2 = (1..6).random()

                scope.launch {
                    snackbarHostState.currentSnackbarData?.dismiss()

                    val message = if (result1 == result2) {
                        "Selamat, anda dapat dadu double!"
                    } else {
                        "Anda belum beruntung!"
                    }
                    snackbarHostState.showSnackbar(
                        message,
                        duration = SnackbarDuration.Short
                    )
                }
            }) {
                Text(stringResource(R.string.roll))
            } }
    }
}

fun getDiceImage(result: Int) : Int {
    return when (result) {
        0 -> R.drawable.dice_0
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
}