package com.example.myproject.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["name"])
data class PinkProfile(
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("url")
    val url: String,
    @field:SerializedName("location")
    val location: String
)