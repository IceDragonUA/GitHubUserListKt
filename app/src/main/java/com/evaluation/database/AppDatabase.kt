package com.evaluation.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.evaluation.search.database.AppUsersDatabaseDao
import com.evaluation.details.database.AppUserDetailDatabaseDao
import com.evaluation.details.model.database.UserDetailsTableItem
import com.evaluation.search.model.item.database.UserTableItem
import com.evaluation.utils.DATABASE_VERSION

@Database(entities = [UserTableItem::class, UserDetailsTableItem::class], version = DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appUsersDao(): AppUsersDatabaseDao

    abstract fun appUserDetailDao(): AppUserDetailDatabaseDao

}






