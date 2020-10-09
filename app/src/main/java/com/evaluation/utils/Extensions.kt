package com.evaluation.utils

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.evaluation.R
import com.evaluation.adapter.viewholders.item.CardItemView
import com.evaluation.adapter.viewholders.item.NoItemView
import com.evaluation.glide.GlideApp

/**
 * @author Vladyslav Havrylenko
 * @since 01.10.2020
 */
fun String?.defIfNull() = this ?: ""
fun Int?.defIfNull(def: Int = 0) = this ?: def

fun empty() = ""

fun ImageView.loadFromUrl(url: String) {
    GlideApp.with(this.context.applicationContext)
        .load(url)
        .into(this)
}