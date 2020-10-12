package com.evaluation.details.dagger

import android.content.Context
import androidx.paging.PagedList
import com.evaluation.database.AppDatabase
import com.evaluation.details.database.AppUserDetailDatabaseDao
import com.evaluation.details.datasource.AppDetailDataSource
import com.evaluation.details.datasource.AppDetailDataSourceFactory
import com.evaluation.details.mapper.UserDetailsMapper
import com.evaluation.details.network.AppUserDetailRestApiDao
import com.evaluation.details.network.AppUserDetailRestApiDaoImpl
import com.evaluation.details.repository.AppUserDetailRepository
import com.evaluation.executor.BaseExecutor
import com.evaluation.network.RestApi
import com.evaluation.search.interaction.AppUserDetailInteraction
import com.evaluation.search.interaction.AppUserDetailInteractionImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import java.util.concurrent.Executor
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataUserDetailModule {

    @Singleton
    @Provides
    fun appRest(appRest: RestApi, executor: BaseExecutor): AppUserDetailRestApiDao = AppUserDetailRestApiDaoImpl(appRest, executor)

    @Provides
    @Singleton
    fun appDao(appDatabase: AppDatabase): AppUserDetailDatabaseDao = appDatabase.appUserDetailDao()

    @Singleton
    @Provides
    fun appRepository(context: Context, mapper: UserDetailsMapper, remoteDao: AppUserDetailRestApiDao, localDao: AppUserDetailDatabaseDao) =
        AppUserDetailRepository(context, mapper, remoteDao, localDao)

    @Singleton
    @Provides
    fun appDataSource(appRepository: AppUserDetailRepository) = AppDetailDataSource(appRepository)

    @Singleton
    @Provides
    fun appDataSourceFactory(appDataSource: AppDetailDataSource) = AppDetailDataSourceFactory(appDataSource)

    @Singleton
    @Provides
    fun appInteraction(factory: AppDetailDataSourceFactory, config: PagedList.Config, networkExecutor: Executor): AppUserDetailInteraction =
        AppUserDetailInteractionImpl(factory, config, networkExecutor)

}