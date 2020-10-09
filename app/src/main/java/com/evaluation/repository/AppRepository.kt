package com.evaluation.repository

import com.evaluation.database.dao.AppDatabaseDao
import com.evaluation.network.dao.AppRestApiDao
import com.evaluation.utils.performData
import javax.inject.Inject


/**
 * @author Vladyslav Havrylenko
 * @since 01.05.2020
 */
class AppRepository @Inject constructor(
    private val appRestApiDao: AppRestApiDao,
    private val appDatabaseDao: AppDatabaseDao
) {

    fun userList(word: String, page: Int) = performData(
        databaseQuery = { appDatabaseDao.userList() },
        networkCall = { appRestApiDao.userList(word, page) },
        saveCallResult = { userList ->
            appDatabaseDao.deleteList()
            appDatabaseDao.insertList(userList.items)
        }
    )
}
