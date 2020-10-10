package com.evaluation.search.network

import com.evaluation.search.model.UserList
import com.evaluation.network.RestApi
import com.evaluation.network.dao.BaseRestApiDao
import javax.inject.Inject

class AppUsersRestApiDao @Inject constructor(private val appRest: RestApi): BaseRestApiDao() {

    fun userListSync(query: String, page: Int, perPage: Int,
                     onPrepared: () -> Unit,
                     onSuccess: (UserList) -> Unit,
                     onError: (String) -> Unit) {

        val request = appRest.userList(query, page, perPage)
        onPrepared()
        syncRequest(request, onSuccess, onError)
    }

    fun userListAsync(query: String, page: Int, perPage: Int,
                      onPrepared: () -> Unit,
                      onSuccess: (UserList) -> Unit,
                      onError: (String) -> Unit) {

        val request = appRest.userList(query, page, perPage)
        onPrepared()
        asyncRequest(request, onSuccess, onError)
    }

}