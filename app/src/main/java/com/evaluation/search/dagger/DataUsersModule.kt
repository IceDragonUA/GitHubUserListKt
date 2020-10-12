package com.evaluation.search.dagger

import android.content.Context
import androidx.paging.PagedList
import com.evaluation.database.AppDatabase
import com.evaluation.details.interaction.AppUsersInteraction
import com.evaluation.details.interaction.AppUsersInteractionImpl
import com.evaluation.executor.BaseExecutor
import com.evaluation.network.RestApi
import com.evaluation.search.database.AppUsersDatabaseDao
import com.evaluation.search.datasource.AppUserDataSource
import com.evaluation.search.datasource.AppUserDataSourceFactory
import com.evaluation.search.mapper.UserMapper
import com.evaluation.search.network.AppUsersRestApiDao
import com.evaluation.search.network.AppUsersRestApiDaoImpl
import com.evaluation.search.repository.AppUsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import java.util.concurrent.Executor
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataUsersModule {

    @Singleton
    @Provides
    fun appRest(appRest: RestApi, executor: BaseExecutor): AppUsersRestApiDao = AppUsersRestApiDaoImpl(appRest, executor)

    @Provides
    @Singleton
    fun appDao(appDatabase: AppDatabase): AppUsersDatabaseDao = appDatabase.appUsersDao()

    @Singleton
    @Provides
    fun appRepository(context: Context, mapper: UserMapper, remoteDao: AppUsersRestApiDao, localDao: AppUsersDatabaseDao) =
        AppUsersRepository(context, mapper, remoteDao, localDao)

    @Singleton
    @Provides
    fun appDataSource(appRepository: AppUsersRepository) = AppUserDataSource(appRepository)

    @Singleton
    @Provides
    fun appDataSourceFactory(appDataSource: AppUserDataSource) = AppUserDataSourceFactory(appDataSource)

    @Singleton
    @Provides
    fun appInteraction(factory: AppUserDataSourceFactory, config: PagedList.Config, networkExecutor: Executor): AppUsersInteraction =
        AppUsersInteractionImpl(factory, config, networkExecutor)

}