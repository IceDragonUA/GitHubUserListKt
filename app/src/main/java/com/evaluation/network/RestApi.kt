package com.evaluation.network

import com.evaluation.details.model.rest.UserDetails
import com.evaluation.search.model.UserList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {

    @GET("search/users")
    fun userList(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<UserList>

    @GET("users/{id}")
    fun userDetails(
        @Path("id") query: String
    ): Call<UserDetails>

}