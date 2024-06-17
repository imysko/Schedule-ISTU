package me.progneo.moodle.domain.usecase

import javax.inject.Inject
import me.progneo.moodle.domain.entities.NotificationsCount
import me.progneo.moodle.domain.repository.NotificationsRepository

interface GetNotificationsCountUseCase {

    suspend operator fun invoke(userId: Int): Result<NotificationsCount>
}

internal class GetNotificationsCountUseCaseImpl @Inject constructor(
    private val notificationsRepository: NotificationsRepository
) : GetNotificationsCountUseCase {

    override suspend operator fun invoke(userId: Int): Result<NotificationsCount> {
        return notificationsRepository.getNotificationsCount(userId)
    }
}
