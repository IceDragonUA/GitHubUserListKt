package com.evaluation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.evaluation.interaction.AppInteraction
import com.evaluation.utils.empty

/**
 * @author Vladyslav Havrylenko
 * @since 07.10.2020
 */
class UserViewModel @ViewModelInject constructor(
    private val interaction: AppInteraction
) : ViewModel() {

    private val query = MutableLiveData<String>()
    private val itemResult = map(query) {
        interaction.searchUsers(it)
    }
    val items = switchMap(itemResult) { it.pagedList }
    val networkState = switchMap(itemResult) { it.networkState }

    init {
        this.query.value = empty()
    }

    fun search(query: String){
        this.query.value = query
    }

}