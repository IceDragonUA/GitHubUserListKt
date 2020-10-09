package com.evaluation.dagger

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.room.Room
import com.evaluation.adapter.viewholders.item.BaseItemView
import com.evaluation.network.RestApi
import com.evaluation.network.dao.AppRestApiDao
import com.evaluation.repository.AppRepository
import com.evaluation.database.AppDatabase
import com.evaluation.database.dao.AppDatabaseDao
import com.evaluation.datasource.UserDataSourceFactory
import com.evaluation.interaction.AppInteraction
import com.evaluation.interaction.AppInteractionImpl
import com.evaluation.utils.BASE_URL
import com.evaluation.utils.DATABASE_NAME
import com.evaluation.utils.PAGE_LIMIT
import com.evaluation.utils.THREADS
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun appRetrofit(client: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    fun appApi(retrofit: Retrofit): RestApi = retrofit.create(RestApi::class.java)

    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder().serializeNulls().create()

    @Provides
    @Singleton
    fun client(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    @Singleton
    @Provides
    fun appRest(appRest: RestApi): AppRestApiDao = AppRestApiDao(appRest)

    @Provides
    @Singleton
    fun appDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Provides
    @Singleton
    fun appDao(appDatabase: AppDatabase): AppDatabaseDao = appDatabase.appDao()

    @Singleton
    @Provides
    fun appRepository(context: Context, remoteDao: AppRestApiDao, localDao: AppDatabaseDao) =
        AppRepository(context, remoteDao, localDao)

    @Singleton
    @Provides
    fun appInteraction(factory: UserDataSourceFactory, config: PagedList.Config, networkExecutor: Executor): AppInteraction =
        AppInteractionImpl(factory, config, networkExecutor)

    @Singleton
    @Provides
    fun config(): PagedList.Config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(PAGE_LIMIT)
        .build()

    @Singleton
    @Provides
    fun executor(): Executor = Executors.newFixedThreadPool(THREADS)

}