package com.example.myproject.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myproject.model.User

@Dao
interface SimpleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM user WHERE firstName = :firstName")
    fun findByFirstName(firstName: String): LiveData<User>
}