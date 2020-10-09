package com.evaluation.network.dao

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

abstract class BaseRestApiDao {

    protected inline fun <T> syncRequest(request: Call<T>,
                               onSuccess: (T) -> Unit,
                               onError: (String) -> Unit) {
        try {
            val response = request.execute()
            val body = response.body()
            if (body != null) onSuccess(body)
            else onError("Network call has failed for a following reason: ${response.code()} ${response.message()}")
        } catch (exception: IOException) {
            onError("Network call has failed for a following reason: ${exception.message ?: exception.toString()}")
        }
    }

    protected inline fun <T> asyncRequest(request: Call<T>,
                                crossinline onSuccess: (T) -> Unit,
                                crossinline onError: (String) -> Unit) {
        request.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, throwable: Throwable) {
                onError("Network call has failed for a following reason: ${throwable.message ?: throwable.toString()}")
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) onSuccess(body)
                    else onError("Network call has failed for a following reason: ${response.code()} ${response.message()}")
                } else {
                    onError("Network call has failed for a following reason: ${response.code()} ${response.message()}")
                }
            }
        })
    }

}