package com.example.activesessionschecker.base

import android.content.Context
import androidx.annotation.StringRes


interface ManageResources {

    fun string(@StringRes id: Int): String

    class Base(private val context: Context) : ManageResources {
        override fun string(id: Int) = context.getString(id)
    }
}