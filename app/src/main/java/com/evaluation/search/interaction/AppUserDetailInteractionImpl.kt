package com.evaluation.search.interaction

import androidx.annotation.MainThread
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.evaluation.adapter.viewholder.item.BaseItemView
import com.evaluation.details.datasource.AppDetailDataSourceFactory
import com.evaluation.utils.Listing
import java.util.concurrent.Executor
import javax.inject.Inject

/**
 * @author Vladyslav Havrylenko
 * @since 09.10.2020
 */
class AppUserDetailInteractionImpl @Inject constructor(
    private val detailFactory: AppDetailDataSourceFactory,
    private val config: PagedList.Config,
    private val networkExecutor: Executor
) : AppUserDetailInteraction {

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
            backgroundState = detailFactory.background,
        )
    }
}
