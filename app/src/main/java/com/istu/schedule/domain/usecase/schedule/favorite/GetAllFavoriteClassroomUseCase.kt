package com.istu.schedule.domain.usecase.schedule.favorite

import com.istu.schedule.domain.entities.schedule.Classroom
import com.istu.schedule.domain.repository.schedule.FavoriteClassroomsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetAllFavoriteClassroomUseCase {

    operator fun invoke(): Flow<List<Classroom>>
}

class GetAllFavoriteClassroomUseCaseImpl @Inject constructor(
    private val favoriteClassroomsRepository: FavoriteClassroomsRepository,
) : GetAllFavoriteClassroomUseCase {

    override fun invoke(): Flow<List<Classroom>> {
        return favoriteClassroomsRepository.getAll()
    }
}