package com.evaluation.details.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.evaluation.search.interaction.AppUserDetailInteraction
import com.evaluation.utils.empty

class DetailViewModel @ViewModelInject constructor(
    private val interaction: AppUserDetailInteraction
) : ViewModel() {

    private val query = MutableLiveData<String>()
    private val itemResult = Transformations.map(query) {
        interaction.userDetails(it)
    }
    val items = Transformations.switchMap(itemResult) { it.pagedList }
    val networkState = Transformations.switchMap(itemResult) { it.networkState }
    val backgroundState = Transformations.switchMap(itemResult) { it.backgroundState }

    init {
        this.query.value = empty()
    }

    fun load(query: String){
        this.query.value = query
    }

}
