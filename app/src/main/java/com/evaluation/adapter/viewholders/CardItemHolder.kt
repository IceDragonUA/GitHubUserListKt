package com.evaluation.adapter.viewholders

import android.view.View
import com.evaluation.adapter.AdapterItemClickListener
import com.evaluation.adapter.viewholders.item.CardItemView
import com.evaluation.utils.loadFromUrl
import kotlinx.android.synthetic.main.user_card_item.view.*


class CardItemHolder(itemView: View, listener: AdapterItemClickListener<CardItemView>?) : BaseViewHolder<CardItemView>(itemView, listener) {

    override fun bind(item: CardItemView) {
        itemView.image.loadFromUrl(item.user.avatar_url)
        itemView.title.text = item.user.login
        itemView.setOnClickListener {
            listener?.onClicked(item)
        }
    }

}