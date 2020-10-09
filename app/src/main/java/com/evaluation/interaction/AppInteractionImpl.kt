package com.evaluation.interaction

import androidx.annotation.MainThread
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.evaluation.adapter.viewholders.item.BaseItemView
import com.evaluation.datasource.UserDataSourceFactory
import com.evaluation.utils.Listing
import java.util.concurrent.Executor
import javax.inject.Inject

/**
 * @author Vladyslav Havrylenko
 * @since 09.10.2020
 */
class AppInteractionImpl @Inject constructor(
    private val factory: UserDataSourceFactory,
    private val config: PagedList.Config,
    private val networkExecutor: Executor
) : AppInteraction {

    @MainThread
    override fun searchUsers(query: String): Listing<BaseItemView> {
        factory.query = query

        val liveList =
            LivePagedListBuilder(factory, config)
                .setFetchExecutor(networkExecutor)
                .build()

        return Listing(
            pagedList = liveList,
            networkState = factory.network,
        )
    }
}
