package com.istu.schedule.domain.usecase.schedule.favorite

import com.istu.schedule.domain.entities.schedule.Teacher
import com.istu.schedule.domain.repository.schedule.FavoriteTeachersRepository
import javax.inject.Inject

interface InsertFavoriteTeacherUseCase {

    suspend operator fun invoke(teacher: Teacher)
}

class InsertFavoriteTeacherUseCaseImpl @Inject constructor(
    private val favoriteTeachersRepository: FavoriteTeachersRepository
) : InsertFavoriteTeacherUseCase {

    override suspend fun invoke(teacher: Teacher) {
        return favoriteTeachersRepository.insert(teacher)
    }
}
