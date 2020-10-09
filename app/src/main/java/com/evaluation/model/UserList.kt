package com.evaluation.model

/**
 * @author Vladyslav Havrylenko
 * @since 07.10.2020
 */
data class UserList(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<User>
)