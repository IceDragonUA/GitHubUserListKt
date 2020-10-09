package com.evaluation.datasource.main

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.evaluation.adapter.viewholders.item.BaseItemView
import com.evaluation.repository.AppRepository
import com.evaluation.utils.NetworkState
import com.evaluation.utils.PAGE_LIMIT
import com.evaluation.utils.PAGE_START
import com.evaluation.utils.empty
import javax.inject.Inject

/**
 * @author Vladyslav Havrylenko
 * @since 08.10.2020
 */
class UserDataSource @Inject constructor(
    private val repository: AppRepository
) : PageKeyedDataSource<Int, BaseItemView>() {

    var query = empty()
    val network = MutableLiveData<Boolean>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, BaseItemView>) {
        repository.userListSync(
            query = query,
            page = PAGE_START,
            perPage = PAGE_LIMIT,
            onPrepared = {
                postInitialState(NetworkState.LOADING)
            },
            onSuccess = { userList ->
                postInitialState(NetworkState.LOADED)
                callback.onResult(userList, null, PAGE_START + 1)
            },
            onError = { userList ->
                postInitialState(NetworkState.LOADED)
                callback.onResult(userList, null, null)
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, BaseItemView>) {
        repository.userListAsync(
            query = query,
            page = params.key,
            perPage = params.requestedLoadSize,
            onPrepared = {
                postAfterState(NetworkState.LOADING)
            },
            onSuccess = { userList ->
                postAfterState(NetworkState.LOADED)
                callback.onResult(userList, params.key + 1)
            },
            onError = {
                postAfterState(NetworkState.LOADED)
                callback.onResult(listOf(), null)
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, BaseItemView>) {

    }

    private fun postInitialState(state: NetworkState) {
        network.postValue(state.value())
    }

    private fun postAfterState(state: NetworkState) {
        network.postValue(state.value())
    }

}
