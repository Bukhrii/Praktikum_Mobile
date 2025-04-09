package com.example.dicecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.dicecompose.component.DiceRollerApp
import com.example.dicecompose.ui.theme.DiceComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceComposeTheme {
                Scaffold( modifier = Modifier.fillMaxSize() )
                { innerPadding ->
                    DiceRollerApp()
                }
            }
        }
    }
}