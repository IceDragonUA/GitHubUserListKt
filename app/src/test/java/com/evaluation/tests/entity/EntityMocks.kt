package com.evaluation.tests.entity

import com.evaluation.details.model.rest.UserDetails
import com.evaluation.search.model.UserList
import com.evaluation.search.model.item.rest.User

object EntityMocks {

    fun userList(): UserList {
        return UserList(
            total_count = 1,
            incomplete_results = false,
            items = listOf(user())
        )
    }

    fun user(): User {
        return User(
            login = "IceDragonUA",
            id = 15745274,
            node_id = "MDQ6VXNlcjE1NzQ1Mjc0",
            avatar_url = "https://avatars1.githubusercontent.com/u/15745274?v=4",
            gravatar_id = "",
            url = "https://api.github.com/users/IceDragonUA",
            html_url = "https://github.com/IceDragonUA",
            followers_url = "https://api.github.com/users/IceDragonUA/followers",
            subscriptions_url = "https://api.github.com/users/IceDragonUA/subscriptions",
            organizations_url = "https://api.github.com/users/IceDragonUA/orgs",
            repos_url = "https://api.github.com/users/IceDragonUA/repos",
            received_events_url = "https://api.github.com/users/IceDragonUA/received_events",
            type = "User",
            score = 1
        )
    }

    fun userDetail(): UserDetails {
        return UserDetails(
            login = "IceDragonUA",
            id = 15745274,
            node_id = "MDQ6VXNlcjE1NzQ1Mjc0",
            avatar_url = "https://avatars1.githubusercontent.com/u/15745274?v=4",
            gravatar_id = "",
            url = "https://api.github.com/users/IceDragonUA",
            html_url = "https://github.com/IceDragonUA",
            followers_url = "https://api.github.com/users/IceDragonUA/followers",
            following_url = "https://api.github.com/users/IceDragonUA/following{/other_user}",
            gists_url = "https://api.github.com/users/IceDragonUA/gists{/gist_id}",
            starred_url = "https://api.github.com/users/IceDragonUA/starred{/owner}{/repo}",
            subscriptions_url = "https://api.github.com/users/IceDragonUA/subscriptions",
            organizations_url = "https://api.github.com/users/IceDragonUA/orgs",
            repos_url = "https://api.github.com/users/IceDragonUA/repos",
            events_url = "https://api.github.com/users/IceDragonUA/events{/privacy}",
            received_events_url = "https://api.github.com/users/IceDragonUA/received_events",
            type = "User",
            site_admin = false,
            name = "Vladyslav",
            company = null,
            blog = "",
            location = null,
            email = null,
            hireable = true,
            bio = null,
            twitter_username = null,
            public_repos = 16,
            public_gists = 0,
            followers = 0,
            following = 0,
            created_at = "2015-11-09T20:37:05Z",
            updated_at = "2020-10-01T15:05:08Z"
        )
    }

}