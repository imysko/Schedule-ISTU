package com.istu.schedule.data.service.notification

import android.app.Service
import android.content.Intent
import android.os.IBinder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import me.progneo.campus.data.preference.LastSeenBlogPostIdManager
import me.progneo.campus.domain.repository.BlogPostRepository
import me.progneo.campus.domain.usecase.GetBlogPostListUseCase
import me.progneo.projfair.domain.repository.CandidateRepository
import me.progneo.projfair.domain.repository.ParticipationRepository

@AndroidEntryPoint
class NotificationService : Service() {

    @Inject
    lateinit var notificationManager: NotificationManager

    @Inject
    lateinit var lastSeenBlogPostIdManager: LastSeenBlogPostIdManager

    @Inject
    lateinit var blogPostRepository: BlogPostRepository

    @Inject
    lateinit var getBlogPostListUseCase: GetBlogPostListUseCase

    @Inject
    lateinit var candidateRepository: CandidateRepository

    @Inject
    lateinit var participationRepository: ParticipationRepository

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private fun observeParticipationList() {
        coroutineScope.launch {
            val canCandidateSendApplicationsFlow =
                candidateRepository.canCandidateSendApplications()

            val isParticipationSentFlow =
                participationRepository.isParticipationSent()

            combine(
                canCandidateSendApplicationsFlow,
                isParticipationSentFlow
            ) { canCandidateSendApplications, isParticipationSent ->
                if (canCandidateSendApplications && !isParticipationSent) {
                    notificationManager.addNotification(Notification.Projfair)
                } else {
                    notificationManager.removeNotification(Notification.Projfair)
                }
            }.collect {}
        }
    }

    private fun observeBlogPost() {
        coroutineScope.launch {
            getBlogPostListUseCase()
            val lastSeenBlogPostIdFlow = lastSeenBlogPostIdManager.getFlow()
            val lastBlogPostIdFlow = blogPostRepository.getLastBlogPostId()

            combine(lastSeenBlogPostIdFlow, lastBlogPostIdFlow) { lastSeenId, lastBlogId ->
                if (lastSeenId < lastBlogId) {
                    notificationManager.addNotification(Notification.Campus)
                } else {
                    notificationManager.removeNotification(Notification.Campus)
                }
            }.collect {}
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
