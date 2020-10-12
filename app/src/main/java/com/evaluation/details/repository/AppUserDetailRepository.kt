package com.evaluation.details.repository

import android.content.Context
import com.evaluation.R
import com.evaluation.adapter.viewholder.item.BaseItemView
import com.evaluation.details.adapter.viewholder.item.DetailItemView
import com.evaluation.adapter.viewholder.item.NoItemView
import com.evaluation.details.database.AppUserDetailDatabaseDao
import com.evaluation.details.mapper.UserDetailsMapper
import com.evaluation.details.network.AppUserDetailRestApiDao
import com.evaluation.details.network.AppUserDetailRestApiDaoImpl
import com.evaluation.utils.defIfNull
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject


/**
 * @author Vladyslav Havrylenko
 * @since 01.05.2020
 */
class AppUserDetailRepository @Inject constructor(
    private val context: Context,
    private val mapper: UserDetailsMapper,
    private val appUserDetailRestApiDao: AppUserDetailRestApiDao,
    private val appUserDetailsDatabaseDao: AppUserDetailDatabaseDao,
) {

    fun userDetailsInit(
        query: String,
        onPrepared: () -> Unit,
        onSuccess: (MutableList<BaseItemView>) -> Unit,
        onError: (MutableList<BaseItemView>) -> Unit
    ): Disposable {
        return appUserDetailRestApiDao.userDetails(query = query)
            .doOnSubscribe {
                onPrepared()
            }
            .subscribe(
                { userDetails ->
                    appUserDetailsDatabaseDao.insertDetail(mapper.toTableItem(userDetails))

                    val itemList: MutableList<BaseItemView> = mutableListOf()
                    appUserDetailsDatabaseDao.userDetail(userDetails.id).let {
                        itemList.add(DetailItemView(id = it.id.defIfNull().toString(), detail = it))
                    }
                    itemList.ifEmpty {
                        itemList.add(
                            NoItemView(
                                title = context.resources.getString(R.string.result).defIfNull()
                            )
                        )
                    }

                    onSuccess(itemList)
                },
                { errorMessage ->
                    Timber.e(errorMessage, "Loading error")

                    val itemList: MutableList<BaseItemView> = mutableListOf()
                    itemList.add(
                        NoItemView(
                            title = context.resources.getString(R.string.result).defIfNull()
                        )
                    )

                    onError(itemList)
                }
            )
    }
}
