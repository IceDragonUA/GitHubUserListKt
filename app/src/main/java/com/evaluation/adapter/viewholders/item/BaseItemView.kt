package com.evaluation.adapter.viewholders.item

import com.evaluation.adapter.factory.TypesFactory

interface BaseItemView {

    var id: String

    fun type(typesFactory: TypesFactory): Int

}