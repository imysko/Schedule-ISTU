package com.istu.schedule.ui.page.schedule.found

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.istu.schedule.data.api.entities.RequestException
import com.istu.schedule.domain.entities.schedule.ScheduleType
import com.istu.schedule.domain.usecase.schedule.GetScheduleOnDayUseCase
import com.istu.schedule.ui.page.schedule.ScheduleViewModel
import com.istu.schedule.ui.util.NavArguments
import com.istu.schedule.ui.util.VibrationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.internal.http.HTTP_NOT_FOUND

@HiltViewModel
class FoundScheduleViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val vibrationManager: VibrationManager,
    private val useCaseScheduleOnDay: GetScheduleOnDayUseCase
) : ScheduleViewModel() {

    private val _uiState = MutableStateFlow<FoundScheduleUiState>(FoundScheduleUiState.Schedule)
    val uiState: StateFlow<FoundScheduleUiState>
        get() = _uiState.asStateFlow()

    private val _scheduleType: ScheduleType = checkNotNull(
        savedStateHandle[NavArguments.SCHEDULE_TYPE]
    )
    private val _scheduleOwnerId: Int = checkNotNull(
        savedStateHandle[NavArguments.SCHEDULE_OWNER_ID]
    )

    init {
        getSchedule(_scheduleType, _scheduleOwnerId)
    }

    override fun selectDate(selectedDate: LocalDate) {
        super.selectDate(selectedDate)
        getSchedule(_scheduleType, _scheduleOwnerId)
    }

    override fun getSchedule(scheduleType: ScheduleType, id: Int) {
        _uiState.tryEmit(FoundScheduleUiState.OnLoading)

        call({
            useCaseScheduleOnDay(
                scheduleType = scheduleType,
                id = id,
                dateString = selectedDate.value.toString()
            )
        }, onSuccess = { studyDay ->
            try {
                if (studyDay.lessons.isEmpty()) {
                    _uiState.tryEmit(FoundScheduleUiState.Weekend)
                } else {
                    _uiState.tryEmit(FoundScheduleUiState.Schedule)
                }

                schedule = studyDay
            } catch (ex: Exception) {
                Log.e("getSchedule", ex.toString())

                _uiState.tryEmit(FoundScheduleUiState.Error(message = ex.message ?: ""))
            }
        }, onError = {
            it as RequestException

            if (it.code == HTTP_NOT_FOUND) {
                _uiState.tryEmit(FoundScheduleUiState.Weekend)
            } else {
                _uiState.tryEmit(FoundScheduleUiState.Error(message = it.message ?: ""))
            }
        }, onNetworkUnavailable = {
            _uiState.tryEmit(FoundScheduleUiState.NoInternetConnection)
        })
    }
}
