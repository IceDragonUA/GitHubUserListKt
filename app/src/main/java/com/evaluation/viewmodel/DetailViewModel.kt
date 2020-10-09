package com.evaluation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.evaluation.repository.AppRepository

class DetailViewModel @ViewModelInject constructor(
    private val repository: AppRepository
) : ViewModel() {

    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean>
        get() = _state

}
