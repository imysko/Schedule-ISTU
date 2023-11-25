package com.istu.schedule.domain.usecase.schedule.favorite

import com.istu.schedule.domain.entities.schedule.Classroom
import com.istu.schedule.domain.repository.schedule.FavoriteClassroomsRepository
import javax.inject.Inject

interface DeleteFavoriteClassroomUseCase {

    suspend operator fun invoke(classroom: Classroom)
}

class DeleteFavoriteClassroomUseCaseImpl @Inject constructor(
    private val favoriteClassroomsRepository: FavoriteClassroomsRepository,
) : DeleteFavoriteClassroomUseCase {

    override suspend fun invoke(classroom: Classroom) {
        return favoriteClassroomsRepository.delete(classroom)
    }
}
