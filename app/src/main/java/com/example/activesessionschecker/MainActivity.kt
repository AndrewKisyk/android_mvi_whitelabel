package com.example.activesessionschecker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme

import com.example.activesessionschecker.util.AppCoroutineScope
import dagger.hilt.android.AndroidEntryPoint
import org.reduxkotlin.TypedStore
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var typedScope: TypedStore<AppState, Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme  {
                AppStore(typedScope) {
                    ActiveSessionNavGraph()
                }
            }
        }
    }
}
