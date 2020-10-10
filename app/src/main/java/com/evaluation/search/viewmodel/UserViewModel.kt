package com.evaluation.search.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.evaluation.details.interaction.AppUsersInteraction
import com.evaluation.utils.empty

/**
 * @author Vladyslav Havrylenko
 * @since 07.10.2020
 */
class UserViewModel @ViewModelInject constructor(
    private val interaction: AppUsersInteraction
) : ViewModel() {

    private var initBackgroundState = true
    private val query = MutableLiveData<String>()
    private val itemResult = map(query) {
        interaction.searchUsers(it, initBackgroundState)
    }
    val items = switchMap(itemResult) { it.pagedList }
    val networkState = switchMap(itemResult) { it.networkState }
    val backgroundState = switchMap(itemResult) { it.backgroundState }

    init {
        this.query.value = empty()
    }

    fun search(query: String, initBackgroundState: Boolean) {
        this.initBackgroundState = initBackgroundState
        this.query.value = query
    }

}