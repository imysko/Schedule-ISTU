package com.example.schedule.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.R
import com.example.schedule.databinding.CardLessonBinding
import com.example.schedule.entities.Lesson

class ScheduleListAdapter(
    private val _lessons: ArrayList<Lesson>
) : RecyclerView.Adapter<ScheduleListAdapter.LessonViewHolder>() {

    class LessonViewHolder(
        val binding: CardLessonBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.card_lesson,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.binding.lesson = _lessons[position]
    }

    override fun getItemCount(): Int = _lessons.size
}