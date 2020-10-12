package com.evaluation.details.network

import com.evaluation.details.model.rest.UserDetails
import com.evaluation.network.RestApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AppUserDetailRestApiDaoImpl @Inject constructor(private val appRest: RestApi) : AppUserDetailRestApiDao {

    override fun userDetails(query: String): Single<UserDetails> {
        return appRest.userDetails(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}