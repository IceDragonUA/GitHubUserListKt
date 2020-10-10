package com.evaluation.details.interaction

import com.evaluation.adapter.viewholder.item.BaseItemView
import com.evaluation.utils.Listing

/**
 * @author Vladyslav Havrylenko
 * @since 09.10.2020
 */
interface AppUsersInteraction {

    fun searchUsers(query: String, initBackgroundState: Boolean): Listing<BaseItemView>

}