package com.rio.mydiary

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rio.mydiary.local.Diary
import com.rio.mydiary.local.DiaryRepository

class MainViewModel(application: Application) : ViewModel() {
    private val mDiaryRepository: DiaryRepository = DiaryRepository(application)
    fun getAllNotes(): LiveData<List<Diary>> = mDiaryRepository.getAllNotes()
    fun insert(diary: Diary) {
        mDiaryRepository.insert(diary)
    }
}