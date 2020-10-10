package com.evaluation.details.mapper

import com.evaluation.details.model.database.UserDetailsTableItem
import com.evaluation.details.model.rest.UserDetails
import com.evaluation.utils.defIfNull
import javax.inject.Inject

/**
 * @author Vladyslav Havrylenko
 * @since 01.10.2020
 */
class UserDetailsMapper @Inject constructor() {

    fun toTableItem(item: UserDetails): UserDetailsTableItem {
        return item.let {
            UserDetailsTableItem(
                login = it.login.defIfNull(),
                id = it.id.defIfNull(),
                node_id = it.node_id.defIfNull(),
                avatar_url = it.avatar_url.defIfNull(),
                gravatar_id = it.gravatar_id.defIfNull(),
                url = it.url.defIfNull(),
                html_url = it.html_url.defIfNull(),
                followers_url = it.followers_url.defIfNull(),
                following_url = it.following_url.defIfNull(),
                gists_url = it.gists_url.defIfNull(),
                starred_url = it.starred_url.defIfNull(),
                subscriptions_url = it.subscriptions_url.defIfNull(),
                organizations_url = it.organizations_url.defIfNull(),
                repos_url = it.repos_url.defIfNull(),
                events_url = it.events_url.defIfNull(),
                received_events_url = it.received_events_url.defIfNull(),
                type = it.type.defIfNull(),
                site_admin = it.site_admin.defIfNull(),
                name = it.name.defIfNull(),
                company = it.company.defIfNull(),
                blog = it.blog.defIfNull(),
                location = it.location.defIfNull(),
                email = it.email.defIfNull(),
                hireable = it.hireable.defIfNull(),
                bio = it.bio.defIfNull(),
                twitter_username = it.twitter_username.defIfNull(),
                public_repos = it.public_repos.defIfNull(),
                public_gists = it.public_gists.defIfNull(),
                followers = it.followers.defIfNull(),
                following = it.following.defIfNull(),
                created_at = it.created_at.defIfNull(),
                updated_at = it.updated_at.defIfNull()
            )
        }
    }
}