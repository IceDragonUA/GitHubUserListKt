package com.evaluation.details.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.evaluation.adapter.viewholder.item.BaseItemView
import com.evaluation.details.repository.AppUserDetailRepository
import com.evaluation.utils.BackgroundState
import com.evaluation.utils.NetworkState
import com.evaluation.utils.empty
import javax.inject.Inject

/**
 * @author Vladyslav Havrylenko
 * @since 08.10.2020
 */
class AppDetailDataSource @Inject constructor(
    private val repository: AppUserDetailRepository
) : PageKeyedDataSource<Int, BaseItemView>() {

    var query = empty()
    val network = MutableLiveData<Boolean>()
    val background = MutableLiveData<Boolean>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, BaseItemView>) {
        repository.userDetailsInit(
            query = query,
            onPrepared = {
                postInitialState(NetworkState.LOADING, BackgroundState.SHOW)
            },
            onSuccess = { userList ->
                postInitialState(NetworkState.LOADED, BackgroundState.HIDE)
                callback.onResult(userList, null, null)
            },
            onError = { userList ->
                postInitialState(NetworkState.LOADED, BackgroundState.SHOW)
                callback.onResult(userList, null, null)
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, BaseItemView>) {

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, BaseItemView>) {

    }

    private fun postInitialState(networkState: NetworkState, backgroundState: BackgroundState) {
        network.postValue(networkState.value())
        background.postValue(backgroundState.value())
    }

}
