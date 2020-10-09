package com.evaluation.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evaluation.model.User

@Dao
interface AppDatabaseDao {

    @Query("SELECT * FROM users")
    fun userList(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(users: List<User>)

    @Query("DELETE FROM users")
    fun deleteList()

}