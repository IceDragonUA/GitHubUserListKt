package com.evaluation.search.network

import com.evaluation.executor.ThreadExecutor
import com.evaluation.network.RestApi
import com.evaluation.search.model.UserList
import io.reactivex.Single
import javax.inject.Inject

class AppUsersRestApiDaoImpl @Inject constructor(
    private val appRest: RestApi,
    private val executor: ThreadExecutor
): AppUsersRestApiDao {

    override fun userList(query: String, page: Int, perPage: Int): Single<UserList> {
        return appRest.userList(query, page, perPage)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

}