package com.example.schedule.ui.horizontalcalendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.R
import com.example.schedule.databinding.ItemHorizontalCalendarDateBinding
import java.time.LocalDate

class HorizontalCalendarAdapter(
    private val clickListener: (LocalDate) -> Unit
) : PagedListAdapter<Day, HorizontalCalendarAdapter.CalendarDateViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Day>() {
            override fun areItemsTheSame(oldItem: Day, newItem: Day): Boolean {
                return true
            }

            override fun areContentsTheSame(oldItem: Day, newItem: Day): Boolean {
                return oldItem == newItem
            }
        }
    }

    class CalendarDateViewHolder(
        val binding: ItemHorizontalCalendarDateBinding
    ) : RecyclerView.ViewHolder(binding.root)

    private var selectedDate: LocalDate = LocalDate.now()

    override fun onBindViewHolder(holder: CalendarDateViewHolder, position: Int) {
        getItem(position)?.let { day ->
            holder.binding.day = day

            holder.itemView.setOnClickListener {
                clickListener(day.date)

                onSelect(day)
                selectedDate = day.date
            }
        }
    }

    private fun onSelect(day: Day) {
        currentList?.binarySearch {
            it.date.compareTo(selectedDate)
        }?.let { currentPosition ->
            onDeselect(currentPosition)
        }

        currentList?.binarySearch {
            it.date.compareTo(day.date)
        }?.let { currentPosition ->
            onSelect(currentPosition)
        }
    }

    private fun onSelect(position: Int) {
        currentList?.let {
            getItem(position)?.isSelected = true
            notifyItemChanged(position)
        }
    }

    private fun onDeselect(position: Int) {
        currentList?.let {
            getItem(position)?.isSelected = false
            notifyItemChanged(position)
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