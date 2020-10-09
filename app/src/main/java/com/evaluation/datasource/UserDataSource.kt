package com.evaluation.datasource

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.evaluation.R
import com.evaluation.adapter.viewholders.item.BaseItemView
import com.evaluation.adapter.viewholders.item.CardItemView
import com.evaluation.adapter.viewholders.item.NoItemView
import com.evaluation.repository.AppRepository
import com.evaluation.utils.Resource
import com.evaluation.utils.defIfNull
import com.evaluation.utils.empty
import com.evaluation.utils.observeOnce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Vladyslav Havrylenko
 * @since 08.10.2020
 */
class UserDataSource @Inject constructor(
    private val context: Context,
    private val repository: AppRepository
) : PageKeyedDataSource<String, BaseItemView>() {

    private val userList: MutableList<BaseItemView> = mutableListOf()

    var state: MutableLiveData<Boolean> = MutableLiveData()

    var word: String = empty()

    private var loaderJob: Job? = null

    private var currentPage = 1

    private var pageCount = 0

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, BaseItemView>) {
        loaderJob?.cancel()
        loaderJob = GlobalScope.launch(Dispatchers.Main) {
            repository.userList(word, currentPage).observeOnce { resource ->
                when (resource.status) {
                    Resource.Status.SUCCESS -> {
                        currentPage = 1
                        userList.clear()
                        resource.data?.forEach {
                            userList.add(CardItemView(id = it.id.defIfNull().toString(), user = it))
                        }
                        userList.ifEmpty {
                            userList.add(NoItemView(title = context.resources.getString(R.string.result).defIfNull()))
                        }
                        currentPage++
                        pageCount = resource.message?.toInt().defIfNull()
                        if (currentPage < pageCount) {
                            currentPage++
                            callback.onResult(userList, null, userList.last().id)
                        } else {
                            currentPage = 1
                            state.value = false
                            callback.onResult(userList, null, null)
                        }
                    }

                    Resource.Status.ERROR -> {
                        state.value = false
                        userList.clear()
                        userList.add(NoItemView(title = context.resources.getString(R.string.result).defIfNull()))
                        callback.onResult(userList, null, null)
                    }

                    Resource.Status.LOADING -> {
                        state.value = true
                    }
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, BaseItemView>) {
        loaderJob?.cancel()
        loaderJob = GlobalScope.launch(Dispatchers.Main) {
            repository.userList(word, currentPage).observeOnce { resource ->
                when (resource.status) {
                    Resource.Status.SUCCESS -> {
                        state.value = false
                        userList.clear()
                        resource.data?.forEach {
                            userList.add(CardItemView(id = it.id.defIfNull().toString(), user = it))
                        }
                        if (currentPage < pageCount) {
                            currentPage++
                            callback.onResult(userList, userList.last().id)
                        } else {
                            currentPage = 1
                            callback.onResult(userList, null)
                        }
                    }

                    Resource.Status.ERROR -> {
                        state.value = false
                    }

                    Resource.Status.LOADING -> {
                        state.value = true
                    }
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, BaseItemView>) {

    }

}
