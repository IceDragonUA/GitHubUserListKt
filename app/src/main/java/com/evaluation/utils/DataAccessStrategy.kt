package com.evaluation.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

fun <T, A> performData(databaseQuery: () -> LiveData<T>,
                               networkCall: suspend () -> Resource<A>,
                               saveCallResult: suspend (A) -> Unit): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)
            emitSource(databaseQuery.invoke().map { Resource.success(responseStatus.message!!, it) })
        } else if (responseStatus.status == Resource.Status.ERROR) {
            emitSource(databaseQuery.invoke().map { Resource.error(responseStatus.message!!, it) })
        }
    }