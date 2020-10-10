package com.evaluation.utils

enum class BackgroundState(private val state: Boolean) {
    SHOW(true),
    HIDE(false);
    fun value() = state
}