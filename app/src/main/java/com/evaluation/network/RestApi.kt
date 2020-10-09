package com.evaluation.network

import com.evaluation.model.UserList
import com.evaluation.utils.PAGE_LIMIT
import com.evaluation.utils.PAGE_START
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("search/users")
    suspend fun userList(
        @Query("q") filter: String,
        @Query("page") page: Int = PAGE_START,
        @Query("per_page") per_page: Int = PAGE_LIMIT
    ): Response<UserList>

}