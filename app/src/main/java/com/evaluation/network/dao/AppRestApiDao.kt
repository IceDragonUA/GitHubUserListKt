package com.evaluation.network.dao

import com.evaluation.network.RestApi
import javax.inject.Inject

class AppRestApiDao @Inject constructor(private val appRest: RestApi): BaseRestApiDao() {

    suspend fun userList(filter: String, page: Int) = result { appRest.userList(filter, page) }

}