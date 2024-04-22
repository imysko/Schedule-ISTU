package me.progneo.campus.data.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import javax.inject.Inject
import me.progneo.campus.data.preference.AuthTokenManager
import me.progneo.campus.data.preference.RefreshTokenManager
import me.progneo.campus.domain.usecase.RefreshTokenUseCase

@HiltWorker
class RefreshTokenWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    @Inject
    lateinit var refreshTokenManager: RefreshTokenManager

    @Inject
    lateinit var authTokenManager: AuthTokenManager

    @Inject
    lateinit var refreshTokenUseCase: RefreshTokenUseCase

    override suspend fun doWork(): Result {
        Log.i(javaClass.simpleName, "Refresh token worker is running")
        refreshTokenManager.get()?.let { refreshToken ->
            refreshTokenUseCase(refreshToken = refreshToken).getOrNull()?.let { tokenResponse ->
                authTokenManager.save(tokenResponse.accessToken)
                refreshTokenManager.save(tokenResponse.refreshToken)
                Log.i(javaClass.simpleName, "Success")
                return Result.success()
            } ?: run {
                Log.i(javaClass.simpleName, "Failure")
                return Result.failure()
            }
        } ?: run {
            Log.i(javaClass.simpleName, "Failure")
            return Result.failure()
        }
    }
}
