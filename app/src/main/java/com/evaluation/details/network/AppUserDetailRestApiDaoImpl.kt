package com.evaluation.details.network

import com.evaluation.details.model.rest.UserDetails
import com.evaluation.executor.ThreadExecutor
import com.evaluation.network.RestApi
import io.reactivex.Single
import javax.inject.Inject

class AppUserDetailRestApiDaoImpl @Inject constructor(
    private val appRest: RestApi,
    private val executor: ThreadExecutor
) : AppUserDetailRestApiDao {

    override fun userDetails(query: String): Single<UserDetails> {
        return appRest.userDetails(query)
            .subscribeOn(executor.mainExecutor)
            .observeOn(executor.postExecutor)
    }

}