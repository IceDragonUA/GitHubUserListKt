package com.evaluation.adapter.factory

import android.view.View
import com.evaluation.R
import com.evaluation.adapter.AdapterItemClickListener
import com.evaluation.adapter.viewholder.BaseViewHolder
import com.evaluation.adapter.viewholder.NoItemHolder
import com.evaluation.search.adapter.viewholder.CardItemHolder
import com.evaluation.details.adapter.viewholder.DetailItemHolder
import com.evaluation.search.adapter.viewholder.item.CardItemView
import com.evaluation.details.adapter.viewholder.item.DetailItemView
import com.evaluation.adapter.viewholder.item.NoItemView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TypesFactoryImpl @Inject constructor() : TypesFactory {

    override fun type(item: NoItemView): Int = R.layout.user_no_item

    override fun type(item: CardItemView): Int = R.layout.user_card_item

    override fun type(item: DetailItemView): Int = R.layout.user_detail_item

    @Suppress("UNCHECKED_CAST")
    override fun holder(type: Int, view: View, listener: AdapterItemClickListener<*>?): BaseViewHolder<*> {
        return when (type) {
            R.layout.user_no_item -> NoItemHolder(view)
            R.layout.user_card_item -> CardItemHolder(view, listener as? AdapterItemClickListener<CardItemView>)
            R.layout.user_detail_item -> DetailItemHolder(view)
            else -> throw RuntimeException("Illegal view type")
        }
    }
}