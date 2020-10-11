package com.evaluation.search.mapper

import com.evaluation.search.model.item.database.UserTableItem
import com.evaluation.search.model.item.rest.User
import com.evaluation.utils.defIfNull
import javax.inject.Inject

/**
 * @author Vladyslav Havrylenko
 * @since 01.10.2020
 */
class UserMapper @Inject constructor() {

    fun toTableItem(item: User): UserTableItem {
        return item.let {
            UserTableItem(
                login = it.login.defIfNull(),
                id = it.id.defIfNull(),
                node_id = it.node_id.defIfNull(),
                avatar_url = it.avatar_url.defIfNull(),
                gravatar_id = it.gravatar_id.defIfNull(),
                url = it.url.defIfNull(),
                html_url = it.html_url.defIfNull(),
                followers_url = it.followers_url.defIfNull(),
                subscriptions_url = it.subscriptions_url.defIfNull(),
                organizations_url = it.organizations_url.defIfNull(),
                repos_url = it.repos_url.defIfNull(),
                received_events_url = it.received_events_url.defIfNull(),
                type = it.type.defIfNull(),
                score = it.score.defIfNull()
            )
        }
    }
}