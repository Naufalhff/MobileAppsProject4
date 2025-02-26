package com.example.pertemuan_1hanyarunrun.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProfileDao {
    @Query("SELECT * FROM profile_table")
    fun getAllProfiles(): LiveData<List<ProfileEntity>>

    @Query("SELECT * FROM profile_table WHERE id = :id LIMIT 1")
    suspend fun getProfileById(id: Int): ProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profile: ProfileEntity)

    @Update
    suspend fun update(profile: ProfileEntity)

    @Delete
    suspend fun delete(profile: ProfileEntity)
}
