package com.evaluation.adapter.factory

import android.view.View
import com.evaluation.R
import com.evaluation.adapter.AdapterItemClickListener
import com.evaluation.adapter.viewholders.BaseViewHolder
import com.evaluation.adapter.viewholders.NoItemHolder
import com.evaluation.adapter.viewholders.CardItemHolder
import com.evaluation.adapter.viewholders.item.CardItemView
import com.evaluation.adapter.viewholders.item.NoItemView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TypesFactoryImpl @Inject constructor() : TypesFactory {

    override fun type(item: NoItemView): Int = R.layout.user_no_item

    override fun type(item: CardItemView): Int = R.layout.user_card_item

    @Suppress("UNCHECKED_CAST")
    override fun holder(type: Int, view: View, listener: AdapterItemClickListener<*>?): BaseViewHolder<*> {
        return when (type) {
            R.layout.user_no_item -> NoItemHolder(view)
            R.layout.user_card_item -> CardItemHolder(view, listener as? AdapterItemClickListener<CardItemView>)
            else -> throw RuntimeException("Illegal view type")
        }
    }
}