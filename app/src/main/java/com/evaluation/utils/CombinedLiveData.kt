package com.evaluation.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

/**
 * @author Vladyslav Havrylenko
 * @since 08.10.2020
 */
class CombinedLiveData<A, B>(ld1: LiveData<A>, ld2: LiveData<B>) : MediatorLiveData<Triple<A?, B?, ModeType>?>() {

    private var a: A? = null
    private var b: B? = null

    init {
        value = Triple(a, b, ModeType.LOADING)

        addSource(ld1) { a: A? ->
            if (a != null) {
                this.a = a
            }
            value = Triple(a, b, ModeType.LOADING)
        }

        addSource(ld2) { b: B? ->
            if (b != null) {
                this.b = b
            }
            value = Triple(a, b, ModeType.UPDATE)
        }
    }
}