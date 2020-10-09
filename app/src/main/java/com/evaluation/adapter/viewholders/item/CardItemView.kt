package com.evaluation.adapter.viewholders.item

import com.evaluation.adapter.factory.TypesFactory
import com.evaluation.model.User

/**
 * @author Vladyslav Havrylenko
 * @since 03.10.2020
 */
data class CardItemView(override var id: String, var user: User) : BaseItemView {

    override fun type(typesFactory: TypesFactory): Int = typesFactory.type(this)

}