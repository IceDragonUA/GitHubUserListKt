package com.evaluation.details.adapter.viewholder

import android.view.View
import com.evaluation.adapter.viewholder.BaseViewHolder
import com.evaluation.details.adapter.viewholder.item.DetailItemView
import com.evaluation.utils.initText
import com.evaluation.utils.loadFromUrl
import kotlinx.android.synthetic.main.user_detail_item.view.*

class DetailItemHolder(itemView: View) : BaseViewHolder<DetailItemView>(itemView, null) {

    override fun bind(item: DetailItemView) {
        itemView.image.loadFromUrl(item.detail.avatar_url)
        itemView.login.initText(item.detail.login)
        itemView.name.initText(item.detail.name)
        itemView.location.initText(item.detail.location)
        itemView.company.initText(item.detail.company)
        itemView.repo.initText(item.detail.html_url)
        itemView.blog.initText(item.detail.blog)
        itemView.bio.initText(item.detail.bio)
        itemView.twitter_username.initText(item.detail.twitter_username)
        itemView.followers.initText(item.detail.followers.toString())
        itemView.following.initText(item.detail.following.toString())
    }

}