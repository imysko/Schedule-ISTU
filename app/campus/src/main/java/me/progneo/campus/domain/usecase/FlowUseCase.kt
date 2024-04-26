package me.progneo.campus.domain.usecase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

abstract class FlowUseCase<T> {

    private val _trigger = MutableStateFlow(true)

    @OptIn(ExperimentalCoroutinesApi::class)
    val resultFlow: Flow<T> = _trigger.flatMapLatest {
        performAction()
    }

    suspend operator fun invoke() {
        _trigger.emit(!(_trigger.value))
    }

    protected abstract fun performAction(): Flow<T>
}
