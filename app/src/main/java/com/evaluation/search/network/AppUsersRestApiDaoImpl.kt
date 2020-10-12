package com.evaluation.search.network

import com.evaluation.network.RestApi
import com.evaluation.search.model.UserList
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AppUsersRestApiDaoImpl @Inject constructor(private val appRest: RestApi): AppUsersRestApiDao {

    override fun userList(query: String, page: Int, perPage: Int): Single<UserList> {
        return appRest.userList(query, page, perPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}