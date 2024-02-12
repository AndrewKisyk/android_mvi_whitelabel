package com.example.activesessionschecker.ui.base

import android.util.Log
import com.example.activesessionschecker.AppState
import org.reduxkotlin.middleware

fun loggerMiddleware() = middleware<AppState> { store, next, action ->
    next(action)
    Log.d("THREAD NAME : ","Logger running on thread ${Thread.currentThread().name}")
    Log.d("LOGGER - ****** :", "******************************************************************")
    Log.d("LOGGER - ACTION : ", action.toString())
    Log.d("LOGGER - STATE : ", "${store.state}")
    Log.d("LOGGER - ****** :", "******************************************************************")
}