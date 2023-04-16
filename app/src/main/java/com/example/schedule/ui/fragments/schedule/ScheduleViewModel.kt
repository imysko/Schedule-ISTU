package com.example.schedule.ui.fragments.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.schedule.entities.Lesson
import com.example.schedule.repositories.ScheduleFirebaseRepository
import com.example.schedule.ui.horizontalcalendar.Day
import com.example.schedule.ui.horizontalcalendar.HorizontalCalendarFactory
import java.time.LocalDate

class ScheduleViewModel(
    private val _repository: ScheduleFirebaseRepository,
    _horizontalCalendarFactory: HorizontalCalendarFactory
) : ViewModel() {

    private val _horizontalCalendarSource: LiveData<PagedList<Day>> =
        _horizontalCalendarFactory.toLiveData(30)
    val horizontalCalendarSource: LiveData<PagedList<Day>> = _horizontalCalendarSource

    private val _institute = MutableLiveData<String>()
    private val _group = MutableLiveData<String>()
    private val _date = MutableLiveData(LocalDate.now())
    val selectedDate: LiveData<LocalDate> = _date

    private val _lessonList: MutableLiveData<ArrayList<Lesson>> = _repository.listRecords
    val lessonList: LiveData<ArrayList<Lesson>> = _lessonList

    fun selectDate(date: LocalDate) {
        _date.value = date
        _repository.updateQuery(_date.value!!, _institute.value!!, _group.value!!)
    }

    fun updateGroup(group: String, institute: String = _institute.value!!) {
        _institute.value = institute
        _group.value = group
        _repository.updateQuery(_date.value!!, institute, group)
    }
}