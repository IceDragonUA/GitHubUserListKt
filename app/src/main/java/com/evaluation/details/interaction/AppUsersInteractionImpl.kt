package com.evaluation.details.interaction

import androidx.annotation.MainThread
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.evaluation.adapter.viewholder.item.BaseItemView
import com.evaluation.search.datasource.AppUserDataSourceFactory
import com.evaluation.utils.Listing
import java.util.concurrent.Executor
import javax.inject.Inject

/**
 * @author Vladyslav Havrylenko
 * @since 09.10.2020
 */
class AppUsersInteractionImpl @Inject constructor(
    private val userFactory: AppUserDataSourceFactory,
    private val config: PagedList.Config,
    private val networkExecutor: Executor
) : AppUsersInteraction {

    @MainThread
    override fun searchUsers(query: String, initBackgroundState: Boolean): Listing<BaseItemView> {
        userFactory.initBackgroundState = initBackgroundState
        userFactory.query = query

        val liveList =
            LivePagedListBuilder(userFactory, config)
                .setFetchExecutor(networkExecutor)
                .build()

        return Listing(
            pagedList = liveList,
            networkState = userFactory.network,
            backgroundState = userFactory.background,
        )
    }

}
