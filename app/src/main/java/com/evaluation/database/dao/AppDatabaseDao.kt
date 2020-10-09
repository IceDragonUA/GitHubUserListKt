package com.evaluation.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evaluation.model.User

@Dao
interface AppDatabaseDao {

    @Query("SELECT * FROM users ORDER BY time ASC")
    fun userList(): List<User>

    @Query("SELECT * FROM users ORDER BY time ASC LIMIT :limit OFFSET :offset ")
    fun userPagedList(limit: Int, offset: Int): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(users: List<User>)

    @Query("DELETE FROM users")
    fun deleteList()

}