package com.evaluation.search.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.evaluation.adapter.viewholder.item.BaseItemView
import com.evaluation.adapter.viewholder.item.NoItemView
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
        repository.userListInit(
            query = query,
            page = PAGE_START,
            perPage = PAGE_LIMIT,
            onPrepared = {
                postInitialState(initNetworkState(), initBackgroundState())
            },
            onSuccess = { userList ->
                val refresh = userList.firstOrNull() is NoItemView
                postInitialState(refreshNetworkState(refresh), refreshBackgroundState(refresh))
                callback.onResult(initList(userList), null, initPaging(refresh))
            },
            onError = { userList ->
                postInitialState(NetworkState.LOADED, BackgroundState.SHOW)
                callback.onResult(initList(userList), null, null)
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, BaseItemView>) {
        repository.userListPaged(
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

    private fun postInitialState(networkState: NetworkState, backgroundState: BackgroundState) {
        network.postValue(networkState.value())
        background.postValue(backgroundState.value())
    }

    private fun postAfterState(networkState: NetworkState) {
        network.postValue(networkState.value())
    }

    private fun initList(userList: MutableList<BaseItemView>) =
        if (query.isEmpty()) listOf() else userList

    private fun initPaging(refresh: Boolean) = if (refresh) null else PAGE_START + 1

    private fun initNetworkState() =
        if (query.isEmpty()) NetworkState.LOADED else NetworkState.LOADING

    private fun initBackgroundState() =
        if (initBackgroundState) BackgroundState.SHOW else BackgroundState.HIDE

    private fun refreshNetworkState(refresh: Boolean) =
        if (refresh) NetworkState.LOADED else NetworkState.LOADING

    private fun refreshBackgroundState(refresh: Boolean) =
        if (refresh) BackgroundState.SHOW else BackgroundState.HIDE

}
