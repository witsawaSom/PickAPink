package com.example.myproject.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myproject.model.User

@Database(
    entities = [
        User::class],
    version = 1,
    exportSchema = false
)
abstract class MyProjectDb : RoomDatabase() {

    abstract fun userDao(): SimpleDao
}