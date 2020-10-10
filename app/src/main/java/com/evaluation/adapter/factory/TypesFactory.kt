package com.evaluation.adapter.factory

import android.view.View
import com.evaluation.adapter.AdapterItemClickListener
import com.evaluation.adapter.viewholder.BaseViewHolder
import com.evaluation.search.adapter.viewholder.item.CardItemView
import com.evaluation.details.adapter.viewholder.item.DetailItemView
import com.evaluation.adapter.viewholder.item.NoItemView

interface TypesFactory {

    fun type(item: NoItemView): Int

    fun type(item: CardItemView): Int

    fun type(item: DetailItemView): Int

    fun holder(type: Int, view: View, listener: AdapterItemClickListener<*>?): BaseViewHolder<*>

}