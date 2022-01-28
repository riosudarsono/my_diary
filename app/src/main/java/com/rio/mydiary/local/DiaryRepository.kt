package com.rio.mydiary.local

import android.app.Application
import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class DiaryRepository(application: Application) {
    private val mNotesDao: DiaryDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = DiaryRoomDatabase.getDatabase(application)
        mNotesDao = db.diaryDao()
    }

    fun getAllNotes(): LiveData<List<Diary>> = mNotesDao.getAllNotes()

    fun insert(note: Diary) {
        executorService.execute { mNotesDao.insert(note) }
    }

    fun delete(note: Diary) {
        executorService.execute { mNotesDao.delete(note) }
    }

    fun update(note: Diary) {
        executorService.execute { mNotesDao.update(note) }
    }
}