package com.evaluation.network

import com.evaluation.model.UserList
import com.evaluation.utils.PAGE_LIMIT
import com.evaluation.utils.PAGE_START
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("search/users")
    fun userList(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<UserList>

}