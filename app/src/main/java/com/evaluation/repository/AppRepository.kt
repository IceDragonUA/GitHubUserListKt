package com.evaluation.repository

import android.content.Context
import com.evaluation.R
import com.evaluation.adapter.viewholders.item.BaseItemView
import com.evaluation.adapter.viewholders.item.CardItemView
import com.evaluation.adapter.viewholders.item.NoItemView
import com.evaluation.database.dao.AppDatabaseDao
import com.evaluation.network.dao.AppRestApiDao
import com.evaluation.utils.defIfNull
import timber.log.Timber
import javax.inject.Inject


/**
 * @author Vladyslav Havrylenko
 * @since 01.05.2020
 */
class AppRepository @Inject constructor(
    private val context: Context,
    private val appRestApiDao: AppRestApiDao,
    private val appDatabaseDao: AppDatabaseDao
) {

    fun userListSync(query: String, page: Int, perPage: Int,
                     onPrepared: () -> Unit,
                     onSuccess: (MutableList<BaseItemView>) -> Unit,
                     onError: (MutableList<BaseItemView>) -> Unit) {
        appRestApiDao.userListSync(
            query = query,
            page = page,
            perPage = perPage,
            onPrepared = {
                onPrepared()
            },
            onSuccess = { userList ->
                appDatabaseDao.deleteList()
                userList.items.forEach {
                    it.time = System.currentTimeMillis()
                }
                appDatabaseDao.insertList(userList.items)

                val itemList: MutableList<BaseItemView> = mutableListOf()
                appDatabaseDao.userList().forEach {
                    itemList.add(CardItemView(id = it.id.defIfNull().toString(), user = it))
                }
                itemList.ifEmpty {
                    itemList.add(NoItemView(title = context.resources.getString(R.string.result).defIfNull()))
                }

                onSuccess(itemList)
            },
            onError = { errorMessage ->
                Timber.e(errorMessage, "Loading error")

                val itemList: MutableList<BaseItemView> = mutableListOf()
                itemList.add(NoItemView(title = context.resources.getString(R.string.result).defIfNull()))

                onError(itemList)
            })
    }

    fun userListAsync(query: String, page: Int, perPage: Int,
                      onPrepared: () -> Unit,
                      onSuccess: (MutableList<BaseItemView>) -> Unit,
                      onError: () -> Unit) {
        appRestApiDao.userListAsync(
            query = query,
            page = page,
            perPage = perPage,
            onPrepared = {
                onPrepared()
            },
            onSuccess = { userList ->
                userList.items.forEach {
                    it.time = System.currentTimeMillis()
                }
                appDatabaseDao.insertList(userList.items)

                val itemList: MutableList<BaseItemView> = mutableListOf()
                appDatabaseDao.userPagedList(perPage, ((page - 1) * perPage)).forEach {
                    itemList.add(CardItemView(id = it.id.defIfNull().toString(), user = it))
                }

                onSuccess(itemList)
            },
            onError = { errorMessage ->
                Timber.e(errorMessage, "Loading error")
                onError()
            })
    }
}
