package com.rio.mydiary.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DiaryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Diary)

    @Update
    fun update(note: Diary)

    @Delete
    fun delete(note: Diary)

    @Query("SELECT * from diary ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Diary>>
}