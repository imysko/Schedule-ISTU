package com.example.schedule.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.schedule.repositories.ScheduleFirebaseRepository
import com.example.schedule.ui.fragments.schedule.ScheduleViewModel
import com.example.schedule.ui.horizontalcalendar.HorizontalCalendarFactory

class ScheduleViewModelFactory(
    private val repository: ScheduleFirebaseRepository,
    private val horizontalCalendarFactory: HorizontalCalendarFactory
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ScheduleViewModel(repository, horizontalCalendarFactory) as T
    }
}
