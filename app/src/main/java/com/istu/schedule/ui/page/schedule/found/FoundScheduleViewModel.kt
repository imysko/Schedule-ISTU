package com.istu.schedule.ui.page.schedule.found

import com.istu.schedule.domain.usecase.schedule.GetScheduleOnDayUseCase
import com.istu.schedule.ui.components.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoundScheduleViewModel @Inject constructor(
    private val _useCaseScheduleOnDay: GetScheduleOnDayUseCase
) : BaseViewModel() {

}