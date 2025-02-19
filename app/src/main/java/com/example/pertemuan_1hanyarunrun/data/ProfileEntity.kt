package com.example.pertemuan_1hanyarunrun.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_table")
data class ProfileEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 1,
    val name: String,
    val studentId: String,
    val email: String
)