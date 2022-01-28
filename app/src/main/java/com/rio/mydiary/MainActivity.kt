package com.rio.mydiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rio.mydiary.adapter.DiaryAdapter
import com.rio.mydiary.helper.ViewModelFactory
import com.rio.mydiary.local.Diary
import com.rio.mydiary.model.DiaryModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: DiaryAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            result.launch(intent)
        }
        setAdapter()

        viewModel = obtainViewModel(this@MainActivity)
        viewModel.getAllNotes().observe(this, Observer {
            adapter.setItems(it)
        })
    }

    private fun setAdapter() {
        adapter = DiaryAdapter(this, ArrayList())
        rv_diary.adapter = adapter
        rv_diary.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    var result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK){
            val name = result.data?.getStringExtra("name")
            val date = result.data?.getStringExtra("date")
            val story = result.data?.getStringExtra("story")
//            val model = DiaryModel(name, date, story)
//            adapter.addItems(model)
            val diary = Diary(title = name, story = story, date = date)
            viewModel.insert(diary)
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(MainViewModel::class.java)
    }

}