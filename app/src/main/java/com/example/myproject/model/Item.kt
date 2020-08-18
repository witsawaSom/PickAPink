package com.example.myproject.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["item"])
data class Item(
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("url")
    val url: String)