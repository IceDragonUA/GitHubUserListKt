package com.evaluation.interaction

import com.evaluation.adapter.viewholders.item.BaseItemView
import com.evaluation.utils.Listing

/**
 * @author Vladyslav Havrylenko
 * @since 09.10.2020
 */
interface AppInteraction {

    fun searchUsers(query: String): Listing<BaseItemView>

}