package com.istu.schedule.domain.usecase.schedule.favorite

import com.istu.schedule.domain.entities.schedule.Group
import com.istu.schedule.domain.repository.schedule.FavoriteGroupsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetAllActiveFavoriteGroupUseCase {

    operator fun invoke(): Flow<List<Group>>
}

class GetAllActiveFavoriteGroupUseCaseImpl @Inject constructor(
    private val favoriteGroupsRepository: FavoriteGroupsRepository,
) : GetAllActiveFavoriteGroupUseCase {

    override fun invoke(): Flow<List<Group>> {
        return favoriteGroupsRepository.getAllActive()
    }
}