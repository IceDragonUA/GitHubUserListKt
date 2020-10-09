package com.evaluation.interaction

import androidx.annotation.MainThread
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.evaluation.adapter.viewholders.item.BaseItemView
import com.evaluation.datasource.detail.DetailDataSourceFactory
import com.evaluation.datasource.main.UserDataSourceFactory
import com.evaluation.utils.Listing
import java.util.concurrent.Executor
import javax.inject.Inject

/**
 * @author Vladyslav Havrylenko
 * @since 09.10.2020
 */
class AppInteractionImpl @Inject constructor(
    private val userFactory: UserDataSourceFactory,
    private val detailFactory: DetailDataSourceFactory,
    private val config: PagedList.Config,
    private val networkExecutor: Executor
) : AppInteraction {

    @MainThread
    override fun searchUsers(query: String): Listing<BaseItemView> {
        userFactory.query = query

        val liveList =
            LivePagedListBuilder(userFactory, config)
                .setFetchExecutor(networkExecutor)
                .build()

        return Listing(
            pagedList = liveList,
            networkState = userFactory.network,
        )
    }

    @MainThread
    override fun userDetails(query: String): Listing<BaseItemView> {
        detailFactory.query = query

        val liveList =
            LivePagedListBuilder(detailFactory, config)
                .setFetchExecutor(networkExecutor)
                .build()

        return Listing(
            pagedList = liveList,
            networkState = detailFactory.network,
        )
    }
}
