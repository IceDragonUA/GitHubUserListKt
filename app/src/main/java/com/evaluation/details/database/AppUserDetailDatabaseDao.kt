package com.evaluation.details.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evaluation.details.model.database.UserDetailsTableItem
import com.evaluation.details.model.rest.UserDetails

@Dao
interface AppUserDetailDatabaseDao {

    @Query("SELECT * FROM user_details WHERE id = :id")
    fun userDetail(id: Int): UserDetailsTableItem

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetail(userDetails: UserDetailsTableItem)

    @Query("DELETE FROM user_details WHERE id = :id")
    fun deleteDetail(id: Int)

}