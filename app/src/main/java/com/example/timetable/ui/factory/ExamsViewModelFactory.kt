package com.example.timetable.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.timetable.repositories.ExamFirebaseRepository
import com.example.timetable.ui.exams.ExamsViewModel

@Suppress("UNCHECKED_CAST")
class ExamsViewModelFactory(
    private val repository: ExamFirebaseRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ExamsViewModel(repository) as T
    }
}