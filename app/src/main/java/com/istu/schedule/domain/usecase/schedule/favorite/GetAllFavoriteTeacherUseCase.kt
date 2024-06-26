package com.istu.schedule.domain.usecase.schedule.favorite

import com.istu.schedule.domain.entities.schedule.Teacher
import com.istu.schedule.domain.repository.schedule.FavoriteTeachersRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

interface GetAllFavoriteTeacherUseCase {

    operator fun invoke(): Flow<List<Teacher>>
}

class GetAllFavoriteTeacherUseCaseImpl @Inject constructor(
    private val favoriteTeachersRepository: FavoriteTeachersRepository
) : GetAllFavoriteTeacherUseCase {

    override fun invoke(): Flow<List<Teacher>> {
        return favoriteTeachersRepository.getAll()
    }
}
