package com.evaluation.search.model

import com.evaluation.search.model.item.rest.User
import com.google.gson.annotations.SerializedName

/**
 * @author Vladyslav Havrylenko
 * @since 07.10.2020
 */
data class UserList(
    @SerializedName("total_count")
    val total_count: Int,
    @SerializedName("incomplete_results")
    val incomplete_results: Boolean,
    @SerializedName("items")
    val items: List<User>
)