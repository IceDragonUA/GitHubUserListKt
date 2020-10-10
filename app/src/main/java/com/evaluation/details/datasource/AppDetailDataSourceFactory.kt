package com.evaluation.details.datasource

import androidx.paging.DataSource
import com.evaluation.adapter.viewholder.item.BaseItemView
import com.evaluation.utils.empty
import javax.inject.Inject

/**
 * @author Vladyslav Havrylenko
 * @since 08.10.2020
 */

class AppDetailDataSourceFactory @Inject constructor(private var dataSource: AppDetailDataSource) :
    DataSource.Factory<Int, BaseItemView>() {

    var query = empty()
    var network = dataSource.network
    var background = dataSource.background

    override fun create(): DataSource<Int, BaseItemView> {
        dataSource.query = query
        return dataSource
    }

}