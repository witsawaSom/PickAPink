package com.example.myproject.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["name"])
data class User(
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("url")
    val url: String,
    @field:SerializedName("first_name")
    val firstName: String,
    @field:SerializedName("last_name")
    val lastName: String)