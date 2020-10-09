package com.evaluation.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.evaluation.model.User
import com.evaluation.database.dao.AppDatabaseDao
import com.evaluation.utils.DATABASE_VERSION

@Database(entities = [User::class], version = DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDatabaseDao

}






