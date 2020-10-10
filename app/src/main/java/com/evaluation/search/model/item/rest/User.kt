package com.evaluation.search.model.item.rest

/**
 * @author Vladyslav Havrylenko@Entity(tableName = "users")
 * @since 07.10.2020
 */
data class User(
    val login: String,
    val id: Int,
    val node_id: String,
    val avatar_url: String,
    val gravatar_id: String,
    val url: String,
    val html_url: String,
    val followers_url: String,
    val subscriptions_url: String,
    val organizations_url: String,
    val repos_url: String,
    val received_events_url: String,
    val type: String,
    val score: Int,
    var time: Long = 0
)