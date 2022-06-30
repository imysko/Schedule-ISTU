package com.example.timetable.ui.fragments.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timetable.entities.Lesson
import com.example.timetable.repositories.ScheduleFirebaseRepository
import java.time.LocalDate

class ScheduleViewModel(
    private val _repository: ScheduleFirebaseRepository
) : ViewModel() {

    private val _institute = MutableLiveData<String>("")
    private val _group = MutableLiveData<String>("")
    private val _date = MutableLiveData<LocalDate>(LocalDate.of(2022, 5, 25))

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