package com.evaluation.utils

import android.widget.ImageView
import com.evaluation.glide.GlideApp

/**
 * @author Vladyslav Havrylenko
 * @since 01.10.2020
 */
fun String?.defIfNull() = this ?: ""
fun Int?.defIfNull(def: Int = 0) = this ?: def
fun Long?.defIfNull(def: Long = 0L) = this ?: def
fun Boolean?.defIfNull(def: Boolean = false) = this ?: def

fun empty() = ""

fun ImageView.loadFromUrl(url: String) {
    GlideApp.with(this.context.applicationContext)
        .load(url)
        .into(this)
}