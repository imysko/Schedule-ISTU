package com.istu.schedule.ui.components.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.viewModelScope
import com.istu.schedule.data.model.RequestException
import java.net.HttpURLConnection
import kotlinx.coroutines.launch

open class BaseViewModel : LifecycleObserver, ViewModel() {

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean>
        get() = _loading
            .distinctUntilChanged()

    private val _unauthorized: MutableLiveData<Boolean> = MutableLiveData()
    val unauthorized: LiveData<Boolean> get() = _unauthorized

    private val _error: LiveEvent<String> = LiveEvent()
    val error: LiveEvent<String> get() = _error

    protected fun <T> call(
        apiCall: suspend () -> Result<T>,
        onSuccess: ((T) -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null,
        handleLoading: Boolean = true,
        handleError: Boolean = true
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
