package com.evaluation.details.adapter.viewholder.item

import com.evaluation.adapter.factory.TypesFactory
import com.evaluation.adapter.viewholder.item.BaseItemView
import com.evaluation.details.model.database.UserDetailsTableItem
import com.evaluation.details.model.rest.UserDetails

/**
 * @author Vladyslav Havrylenko
 * @since 03.10.2020
 */
data class DetailItemView(override var id: String, var detail: UserDetailsTableItem) : BaseItemView {

    override fun type(typesFactory: TypesFactory): Int = typesFactory.type(this)

}