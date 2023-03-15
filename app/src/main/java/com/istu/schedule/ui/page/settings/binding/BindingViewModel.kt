package com.istu.schedule.ui.page.settings.binding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.istu.schedule.domain.model.schedule.Institute
import com.istu.schedule.domain.usecase.schedule.GetInstitutesListUseCase
import com.istu.schedule.ui.components.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BindingViewModel @Inject constructor(
    private val useCase: GetInstitutesListUseCase
) : BaseViewModel() {

    private val _institutesList = MutableLiveData<List<Institute>>()
    val institutesList: LiveData<List<Institute>> = _institutesList

    fun getInstitutesList() {
        call({
            useCase.getInstitutesList()
        }, onSuccess = {
            _institutesList.postValue(it)
        })
    }
}