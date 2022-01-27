package com.rio.mydiary.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.rio.mydiary.R
import com.rio.mydiary.model.DiaryModel
import kotlinx.android.synthetic.main.view_diary.view.*
import java.util.concurrent.Executors

class DiaryAdapter(private val activity: Activity?, private var data: MutableList<DiaryModel>) : RecyclerView.Adapter<DiaryAdapter.Holder>() {

    fun addItems(data: DiaryModel) {
        this.data.add(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_diary, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(data[position], position)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(mData: DiaryModel, position: Int) {
            with(itemView) {
                tv_name.text = mData.name
                tv_date.text = mData.date
                tv_story.text = mData.story
            }
        }
    }
}