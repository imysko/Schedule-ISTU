package com.istu.schedule.domain.usecase.schedule.favorite

import com.istu.schedule.domain.entities.schedule.Group
import com.istu.schedule.domain.repository.schedule.FavoriteGroupsRepository
import javax.inject.Inject

interface InsertFavoriteGroupUseCase {

    suspend operator fun invoke(group: Group)
}

class InsertFavoriteGroupUseCaseImpl @Inject constructor(
    private val favoriteGroupsRepository: FavoriteGroupsRepository
) : InsertFavoriteGroupUseCase {

    override suspend fun invoke(group: Group) {
        return favoriteGroupsRepository.insert(group)
    }
}
