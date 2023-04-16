package com.example.schedule.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.schedule.repositories.ExamFirebaseRepository
import com.example.schedule.ui.fragments.exams.ExamsViewModel

@Suppress("UNCHECKED_CAST")
class ExamsViewModelFactory(
    private val repository: ExamFirebaseRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ExamsViewModel(repository) as T
    }
}