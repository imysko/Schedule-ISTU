package com.istu.schedule.ui.components.base

import androidx.lifecycle.*
import com.istu.schedule.data.model.RequestException
import kotlinx.coroutines.launch
import java.net.HttpURLConnection

open class BaseViewModel : LifecycleObserver, ViewModel() {

    val loading: LiveData<Boolean>
        get() = _loading
            .distinctUntilChanged()

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()

    val unauthorized: LiveData<Boolean> get() = _unauthorized
    private val _unauthorized: MutableLiveData<Boolean> = MutableLiveData()

    val error: LiveEvent<String> get() = _error
    private val _error: LiveEvent<String> = LiveEvent()

    protected fun <T> call(
        apiCall: suspend () -> Result<T>,
        onSuccess: ((T) -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null,
        handleLoading: Boolean = true,
        handleError: Boolean = true,
    ) = viewModelScope.launch {
        if (handleLoading) {
            _loading.postValue(true)
        }

        val result = apiCall.invoke()

        if (handleLoading) {
            _loading.postValue(false)
        }

        result.getOrNull()?.let { value ->
            onSuccess?.invoke(value)
        }

        result.exceptionOrNull()?.let { error ->
            onError?.invoke(error)
            if (handleError) {
                onCallError(error)
            }
        }
    }

    protected fun onCallError(error: Throwable) {
        checkIsUnauthorized(error)
        setError(error.message.orEmpty())
    }

    protected fun setLoading(isLoading: Boolean) = _loading.postValue(isLoading)

    protected fun setError(errorMessage: String) = _error.postValue(errorMessage)

    private fun checkIsUnauthorized(error: Throwable) {
        if (error is RequestException && error.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            _unauthorized.postValue(true)
        }
    }
}