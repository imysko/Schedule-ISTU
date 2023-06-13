package com.istu.schedule.ui.page.onboarding

import com.istu.schedule.ui.components.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OnBoardingViewModel : BaseViewModel() {

    private val _onBoardingUiState = MutableStateFlow(OnBoardingUiState())
    val onBoardingUiState: StateFlow<OnBoardingUiState> = _onBoardingUiState.asStateFlow()

    fun onNextButtonClick() {
        _onBoardingUiState.update {
            when (it.pageNumber) {
                1 -> {
                    it.copy(
                        pageNumber = it.pageNumber + 1,
                    )
                }
                2 -> {
                    it.copy(
                        pageNumber = it.pageNumber + 1,
                        isShowNextButton = false,
                        isShowSetupScheduleButton = true,
                        isShowSkipSetupScheduleButton = true,
                    )
                }
                else -> it.copy()
            }
        }
    }

    fun onSetupScheduleButtonClick() {
        _onBoardingUiState.update {
            it.copy(
                pageNumber = it.pageNumber + 1,
                isShowSetupScheduleButton = false,
                isShowSkipSetupScheduleButton = false,
                isShowAuthorizationButton = true,
                isShowSkipAuthorizationButton = true,
            )
        }
    }

    fun onAuthorizationButtonClick() {
        _onBoardingUiState.update {
            it.copy(
                isShowAuthorizationButton = false,
                isShowSkipAuthorizationButton = false,
                canNavigateToMainPage = true,
            )
        }
    }

    data class OnBoardingUiState(
        val pageNumber: Int = 1,
        val isShowNextButton: Boolean = true,
        val isShowSetupScheduleButton: Boolean = false,
        val isShowSkipSetupScheduleButton: Boolean = false,
        val isShowAuthorizationButton: Boolean = false,
        val isShowSkipAuthorizationButton: Boolean = false,
        val canNavigateToMainPage: Boolean = false,
    )
}