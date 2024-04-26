package com.istu.schedule.data.service.notification

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import me.progneo.campus.data.preference.LastSeenBlogPostIdManager
import me.progneo.campus.domain.repository.BlogPostRepository
import me.progneo.projfair.domain.repository.ParticipationRepository
import me.progneo.projfair.domain.usecase.GetProjectListUseCase

@AndroidEntryPoint
class NotificationService : Service() {

    @Inject
    lateinit var notificationManager: NotificationManager

    @Inject
    lateinit var lastSeenBlogPostIdManager: LastSeenBlogPostIdManager

    @Inject
    lateinit var blogPostRepository: BlogPostRepository

    @Inject
    lateinit var getProjectListUseCase: GetProjectListUseCase

    @Inject
    lateinit var participationRepository: ParticipationRepository

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private fun observeParticipationList() {
        coroutineScope.launch {
            try {
                participationRepository.getParticipationList()
                val hasProjectsWithCollectParticipation = findProjectsWithCollectParticipation()
                val isParticipationSentFlow = participationRepository.isParticipationSent()

                isParticipationSentFlow.collect { isParticipationSent ->
                    if (hasProjectsWithCollectParticipation && !isParticipationSent) {
                        notificationManager.addNotification(Notification.Projfair)
                    } else {
                        notificationManager.removeNotification(Notification.Projfair)
                    }
                }
            } catch (ex: Exception) {
                Log.e(NotificationService::class.simpleName, ex.stackTraceToString())
            }
        }
    }

    private fun observeBlogPost() {
        coroutineScope.launch {
            try {
                blogPostRepository.getBlogPostList()
                val lastSeenBlogPostIdFlow = lastSeenBlogPostIdManager.getFlow()
                val lastBlogPostIdFlow = blogPostRepository.getLastBlogPostId()

                combine(lastSeenBlogPostIdFlow, lastBlogPostIdFlow) { lastSeenId, lastBlogId ->
                    if (lastSeenId < lastBlogId) {
                        notificationManager.addNotification(Notification.Campus)
                    } else {
                        notificationManager.removeNotification(Notification.Campus)
                    }
                }.collect {}
            } catch (ex: Exception) {
                Log.e(NotificationService::class.simpleName, ex.stackTraceToString())
            }
        }
    }

    private suspend fun findProjectsWithCollectParticipation(): Boolean {
        try {
            val projectListResult = getProjectListUseCase(page = 0, states = listOf(1))

            projectListResult.onSuccess { projectList ->
                return projectList.isNotEmpty()
            }

            return false
        } catch (ex: Exception) {
            Log.e(NotificationService::class.simpleName, ex.stackTraceToString())
            return false
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        observeParticipationList()
        observeBlogPost()
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}
