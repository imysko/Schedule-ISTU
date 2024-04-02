package com.istu.schedule.domain.usecase.schedule.favorite

import com.istu.schedule.domain.entities.schedule.Classroom
import com.istu.schedule.domain.repository.schedule.FavoriteClassroomsRepository
import javax.inject.Inject

interface InsertFavoriteClassroomUseCase {

    suspend operator fun invoke(classroom: Classroom)
}

class InsertFavoriteClassroomUseCaseImpl @Inject constructor(
    private val favoriteClassroomsRepository: FavoriteClassroomsRepository
) : InsertFavoriteClassroomUseCase {

    override suspend fun invoke(classroom: Classroom) {
        return favoriteClassroomsRepository.insert(classroom)
    }
}
