package com.evaluation.search.repository

import android.content.Context
import com.evaluation.R
import com.evaluation.adapter.viewholder.item.BaseItemView
import com.evaluation.search.adapter.viewholder.item.CardItemView
import com.evaluation.adapter.viewholder.item.NoItemView
import com.evaluation.search.database.AppUsersDatabaseDao
import com.evaluation.search.mapper.UserMapper
import com.evaluation.search.network.AppUsersRestApiDao
import com.evaluation.search.network.AppUsersRestApiDaoImpl
import com.evaluation.utils.defIfNull
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject


/**
 * @author Vladyslav Havrylenko
 * @since 01.05.2020
 */
class AppUsersRepository @Inject constructor(
    private val context: Context,
    private val mapper: UserMapper,
    private val appUsersRestApiDao: AppUsersRestApiDao,
    private val appUsersDatabaseDao: AppUsersDatabaseDao
) {

    fun userListInit(
        query: String,
        page: Int,
        perPage: Int,
        onPrepared: () -> Unit,
        onSuccess: (MutableList<BaseItemView>) -> Unit,
        onError: (MutableList<BaseItemView>) -> Unit
    ): Disposable {
        return appUsersRestApiDao.userList(query = query, page = page, perPage = perPage)
            .doOnSubscribe {
                onPrepared()
            }
            .subscribe(
                { userList ->
                    appUsersDatabaseDao.deleteList()
                    appUsersDatabaseDao.insertList(
                        userList.items.map {
                            mapper.toTableItem(it)
                        }
                    )

                    val itemList: MutableList<BaseItemView> = mutableListOf()
                    appUsersDatabaseDao.userList().forEach {
                        itemList.add(CardItemView(id = it.id.defIfNull().toString(), user = it))
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

    fun userListPaged(
        query: String,
        page: Int,
        perPage: Int,
        onPrepared: () -> Unit,
        onSuccess: (MutableList<BaseItemView>) -> Unit,
        onError: () -> Unit
    ): Disposable {
        return appUsersRestApiDao.userList(query = query, page = page, perPage = perPage)
            .doOnSubscribe {
                onPrepared()
            }
            .subscribe(
                { userList ->
                    appUsersDatabaseDao.insertList(
                        userList.items.map {
                            mapper.toTableItem(it)
                        }
                    )

                    val itemList: MutableList<BaseItemView> = mutableListOf()
                    appUsersDatabaseDao.userPagedList(perPage, ((page - 1) * perPage)).forEach {
                        itemList.add(CardItemView(id = it.id.defIfNull().toString(), user = it))
                    }

                    onSuccess(itemList)
                },
                { errorMessage ->
                    Timber.e(errorMessage, "Loading error")
                    onError()
                }
            )
    }
}
