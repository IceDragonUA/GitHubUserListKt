package com.evaluation.search.model

import com.evaluation.search.model.item.rest.User

/**
 * @author Vladyslav Havrylenko
 * @since 07.10.2020
 */
data class UserList(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<User>
)