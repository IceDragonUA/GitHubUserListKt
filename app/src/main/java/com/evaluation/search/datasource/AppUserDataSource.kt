package com.evaluation.search.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.evaluation.adapter.viewholder.item.BaseItemView
import com.evaluation.search.repository.AppUsersRepository
import com.evaluation.utils.*
import javax.inject.Inject

/**
 * @author Vladyslav Havrylenko
 * @since 08.10.2020
 */
class AppUserDataSource @Inject constructor(
    private val repository: AppUsersRepository
) : PageKeyedDataSource<Int, BaseItemView>() {

    var initBackgroundState = true
    var query = empty()
    val network = MutableLiveData<Boolean>()
    val background = MutableLiveData<Boolean>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, BaseItemView>) {
        repository.userListSync(
            query = query,
            page = PAGE_START,
            perPage = PAGE_LIMIT,
            onPrepared = {
                postInitialState(NetworkState.LOADING, initBackgroundState())
            },
            onSuccess = { userList ->
                postInitialState(NetworkState.LOADING, BackgroundState.HIDE)
                callback.onResult(userList, null, PAGE_START + 1)
            },
            onError = { userList ->
                postInitialState(NetworkState.LOADED, BackgroundState.SHOW)
                callback.onResult(userList, null, null)
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, BaseItemView>) {
        repository.userListAsync(
            query = query,
            page = params.key,
            perPage = params.requestedLoadSize,
            onPrepared = {
                postAfterState(NetworkState.LOADING, BackgroundState.HIDE)
            },
            onSuccess = { userList ->
                postAfterState(NetworkState.LOADED, BackgroundState.HIDE)
                callback.onResult(userList, params.key + 1)
            },
            onError = {
                postAfterState(NetworkState.LOADED, BackgroundState.HIDE)
                callback.onResult(listOf(), null)
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, BaseItemView>) {

    }

    private fun postInitialState(networkState: NetworkState, backgroundState: BackgroundState) {
        network.postValue(networkState.value())
        background.postValue(backgroundState.value())
    }

    private fun postAfterState(networkState: NetworkState, backgroundState: BackgroundState) {
        network.postValue(networkState.value())
        background.postValue(backgroundState.value())
    }

    private fun initBackgroundState() = if (initBackgroundState) BackgroundState.SHOW else BackgroundState.HIDE

}
