package com.evaluation.details.network

import com.evaluation.details.model.rest.UserDetails
import com.evaluation.network.RestApi
import com.evaluation.network.dao.BaseRestApiDao
import javax.inject.Inject

class AppUserDetailRestApiDao @Inject constructor(private val appRest: RestApi): BaseRestApiDao() {

    fun userDetailsSync(query: String,
                        onPrepared: () -> Unit,
                        onSuccess: (UserDetails) -> Unit,
                        onError: (String) -> Unit) {

        val request = appRest.userDetails(query)
        onPrepared()
        syncRequest(request, onSuccess, onError)
    }

}