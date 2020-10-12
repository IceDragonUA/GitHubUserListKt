package com.evaluation.search.network

import com.evaluation.search.model.UserList
import io.reactivex.Single

interface AppUsersRestApiDao {

    fun userList(query: String, page: Int, perPage: Int): Single<UserList>

}