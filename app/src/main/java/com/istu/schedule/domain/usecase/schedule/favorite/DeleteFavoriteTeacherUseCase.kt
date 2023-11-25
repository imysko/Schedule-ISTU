package com.istu.schedule.domain.usecase.schedule.favorite

import com.istu.schedule.domain.entities.schedule.Teacher
import com.istu.schedule.domain.repository.schedule.FavoriteTeachersRepository
import javax.inject.Inject

interface DeleteFavoriteTeacherUseCase {

    suspend operator fun invoke(teacher: Teacher)
}

class DeleteFavoriteTeacherUseCaseImpl @Inject constructor(
    private val favoriteTeachersRepository: FavoriteTeachersRepository,
) : DeleteFavoriteTeacherUseCase {

    override suspend fun invoke(teacher: Teacher) {
        return favoriteTeachersRepository.delete(teacher)
    }
}
