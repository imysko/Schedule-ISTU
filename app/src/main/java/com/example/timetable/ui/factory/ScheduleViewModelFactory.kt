package com.example.timetable.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.timetable.repositories.ScheduleFirebaseRepository
import com.example.timetable.ui.fragments.schedule.ScheduleViewModel

@Suppress("UNCHECKED_CAST")
class ScheduleViewModelFactory(
    private val repository: ScheduleFirebaseRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ScheduleViewModel(repository) as T
    }
}
