package com.evaluation.search.model.item.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * @author Vladyslav Havrylenko@Entity(tableName = "users")
 * @since 07.10.2020
 */
@Entity(tableName = "users", indices = [Index(value = ["id"], unique = true)])
data class UserTableItem(
    @PrimaryKey(autoGenerate = true)
    var index: Int? = null,
    val login: String,
    @ColumnInfo(name = "id")
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
)