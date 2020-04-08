package com.ravimhzn.infosyscodingapplication.utils.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

fun ViewGroup?.inflateLayout(resource: Int, attachToRoot: Boolean): View =
    LayoutInflater.from(this?.context).inflate(resource, this, attachToRoot)

fun ViewGroup?.inflateCustomLayout(resource: Int): View =
    inflateLayout(resource, true)

fun TextView.setTextOrEmpty(textId: String?) =
    textId?.let {
        text = it
    } ?: run {
        text = ""
    }