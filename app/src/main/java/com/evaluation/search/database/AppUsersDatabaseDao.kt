package com.evaluation.search.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evaluation.search.model.item.database.UserTableItem
import com.evaluation.search.model.item.rest.User

@Dao
interface AppUsersDatabaseDao {

    @Query("SELECT * FROM users ORDER BY time ASC")
    fun userList(): List<UserTableItem>

    @Query("SELECT * FROM users ORDER BY time ASC LIMIT :limit OFFSET :offset ")
    fun userPagedList(limit: Int, offset: Int): List<UserTableItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(users: List<UserTableItem>)

    @Query("DELETE FROM users")
    fun deleteList()

}