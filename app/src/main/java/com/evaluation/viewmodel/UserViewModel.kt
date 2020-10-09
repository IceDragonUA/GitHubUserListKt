package com.evaluation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.evaluation.datasource.UserDataSourceFactory
import com.evaluation.utils.CombinedLiveData
import com.evaluation.utils.PAGE_LIMIT
import com.evaluation.utils.empty

/**
 * @author Vladyslav Havrylenko
 * @since 07.10.2020
 */
class UserViewModel @ViewModelInject constructor(
    private val dataSourceFactory: UserDataSourceFactory
) : ViewModel() {

    private val _word = MutableLiveData<String>()

    init {
        _word.value = empty()
    }

    fun search(word: String) {
        _word.value = word
    }

    private val state = dataSourceFactory.state

    private val users = _word.switchMap {
        dataSourceFactory.word = it
        val pagedListConfig =
            PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_LIMIT)
                .build()
        LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()
    }

    val iterator = CombinedLiveData(state, users)

}