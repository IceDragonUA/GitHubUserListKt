package com.evaluation.adapter.viewholders

import android.view.View
import com.evaluation.adapter.viewholders.item.NoItemView
import kotlinx.android.synthetic.main.user_no_item.view.*

class NoItemHolder(itemView: View) : BaseViewHolder<NoItemView>(itemView, null) {

    override fun bind(item: NoItemView) {
        itemView.result.text = item.title
    }

}