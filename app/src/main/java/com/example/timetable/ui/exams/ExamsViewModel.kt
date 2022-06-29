package com.example.timetable.ui.exams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timetable.objects.Exam
import com.example.timetable.repositories.ExamFirebaseRepository

class ExamsViewModel (
    private val _repository: ExamFirebaseRepository
        ): ViewModel() {

    private val _institute = MutableLiveData<String>()
    private val _group = MutableLiveData<String>()

    val header: LiveData<String> = _group

    private val _examList: MutableLiveData<ArrayList<Exam>> = _repository.listRecords
    val examList: LiveData<ArrayList<Exam>> = _examList

    private val _examListIsEmpty: MutableLiveData<Boolean> = _repository.examListIsEmpty
    val examListIsEmpty: LiveData<Boolean> = _examListIsEmpty

    fun updateGroup(group: String, institute: String = _institute.value!!) {
        _institute.value = institute
        _group.value = group
        _repository.updateQuery(institute, group)
    }
}
