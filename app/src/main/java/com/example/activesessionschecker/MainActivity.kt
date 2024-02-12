package com.example.activesessionschecker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme

import com.example.activesessionschecker.util.AppCoroutineScope
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme  {
                AppStore(coroutineScope = AppCoroutineScope) {
                    ActiveSessionNavGraph()
                }
            }
        }
    }
}
