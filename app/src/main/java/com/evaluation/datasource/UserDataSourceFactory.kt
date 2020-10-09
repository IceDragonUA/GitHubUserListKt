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
    DataSource.Factory<Int, BaseItemView>() {

    var query = empty()
    var network = dataSource.network

    override fun create(): DataSource<Int, BaseItemView> {
        dataSource.query = query
        return dataSource
    }

}