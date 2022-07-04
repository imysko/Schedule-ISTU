package com.example.timetable.ui.horizontalcalendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.timetable.R
import com.example.timetable.databinding.ItemHorizontalCalendarDateBinding
import java.time.LocalDate

class HorizontalCalendarAdapter(
    private val clickListener: (LocalDate) -> Unit
) : PagedListAdapter<LocalDate, HorizontalCalendarAdapter.CalendarDateViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LocalDate>() {
            override fun areItemsTheSame(oldItem: LocalDate, newItem: LocalDate): Boolean {
                return true
            }

            override fun areContentsTheSame(oldItem: LocalDate, newItem: LocalDate): Boolean {
                return oldItem == newItem
            }
        }
    }

    class CalendarDateViewHolder(
        val binding: ItemHorizontalCalendarDateBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: CalendarDateViewHolder, position: Int) {
        getItem(position)?.let { day ->
            holder.binding.day = day

            holder.itemView.setOnClickListener {
                clickListener(day)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarDateViewHolder {
        return CalendarDateViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_horizontal_calendar_date,
                parent,
                false
            )
        )
    }
}