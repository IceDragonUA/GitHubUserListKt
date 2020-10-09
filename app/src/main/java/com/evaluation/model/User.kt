package com.evaluation.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Vladyslav Havrylenko
 * @since 07.10.2020
 */
@Entity(tableName = "users")
data class User(
    val login: String,
    @PrimaryKey
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