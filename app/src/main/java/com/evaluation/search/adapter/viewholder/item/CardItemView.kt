package com.evaluation.search.adapter.viewholder.item

import com.evaluation.adapter.factory.TypesFactory
import com.evaluation.adapter.viewholder.item.BaseItemView
import com.evaluation.search.model.item.database.UserTableItem
import com.evaluation.search.model.item.rest.User

/**
 * @author Vladyslav Havrylenko
 * @since 03.10.2020
 */
data class CardItemView(override var id: String, var user: UserTableItem) : BaseItemView {

    override fun type(typesFactory: TypesFactory): Int = typesFactory.type(this)

}