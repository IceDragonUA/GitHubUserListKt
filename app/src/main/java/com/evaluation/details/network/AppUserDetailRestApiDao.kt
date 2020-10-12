package com.evaluation.details.network

import com.evaluation.details.model.rest.UserDetails
import io.reactivex.Single

interface AppUserDetailRestApiDao {

    fun userDetails(query: String): Single<UserDetails>

}