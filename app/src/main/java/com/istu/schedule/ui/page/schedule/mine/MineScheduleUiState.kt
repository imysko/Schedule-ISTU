package com.istu.schedule.ui.page.schedule.mine

sealed class MineScheduleUiState(
    open val description: String?,
) {
    data class NoInternetConnection(
        override val description: String,
    ) : MineScheduleUiState(description = description)

    data object UnknownUser : MineScheduleUiState(description = null)

    data class Weekend(
        override val description: String,
    ) : MineScheduleUiState(description = description)

    data class Schedule(
        override val description: String,
    ) : MineScheduleUiState(description = description)

    data class Error(
        override val description: String,
        val message: String,
    ) : MineScheduleUiState(description = description)

    data class OnLoading(
        override val description: String,
    ) : MineScheduleUiState(description = description)
}
