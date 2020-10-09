package com.evaluation.datasource

import androidx.paging.DataSource
import com.evaluation.adapter.viewholders.item.BaseItemView
import com.evaluation.utils.empty
import javax.inject.Inject

/**
 * @author Vladyslav Havrylenko
 * @since 08.10.2020
 */

class UserDataSourceFactory @Inject constructor(private var dataSource: UserDataSource) :
    DataSource.Factory<String, BaseItemView>() {

    var state = dataSource.state

    var word: String = empty()

    override fun create(): DataSource<String, BaseItemView> {
        dataSource.word = word
        return dataSource
    }

}