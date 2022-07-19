package com.example.timetable.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.timetable.R
import com.example.timetable.databinding.CardExamBinding
import com.example.timetable.entities.Exam

class ExamListAdapter(
    private val _exams: ArrayList<Exam>
) : RecyclerView.Adapter<ExamListAdapter.ExamViewHolder>() {

    class ExamViewHolder(
        val binding: CardExamBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamViewHolder {
        return ExamViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.card_exam,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ExamViewHolder, position: Int) {
        holder.binding.exam = _exams[position]
    }

    override fun getItemCount(): Int = _exams.size
}