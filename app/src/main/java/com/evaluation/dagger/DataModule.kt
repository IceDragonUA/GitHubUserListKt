package com.evaluation.dagger

import android.content.Context
import androidx.room.Room
import com.evaluation.network.RestApi
import com.evaluation.network.dao.AppRestApiDao
import com.evaluation.repository.AppRepository
import com.evaluation.database.AppDatabase
import com.evaluation.database.dao.AppDatabaseDao
import com.evaluation.utils.BASE_URL
import com.evaluation.utils.DATABASE_NAME
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun appRetrofit(gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    fun appApi(retrofit: Retrofit): RestApi = retrofit.create(RestApi::class.java)

    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder().serializeNulls().create()

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
    fun appRepository(remoteDao: AppRestApiDao, localDao: AppDatabaseDao) = AppRepository(remoteDao, localDao)

}