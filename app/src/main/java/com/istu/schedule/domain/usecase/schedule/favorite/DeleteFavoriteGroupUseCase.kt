package com.istu.schedule.domain.usecase.schedule.favorite

import com.istu.schedule.domain.entities.schedule.Group
import com.istu.schedule.domain.repository.schedule.FavoriteGroupsRepository
import javax.inject.Inject

interface DeleteFavoriteGroupUseCase {

    suspend operator fun invoke(group: Group)
}

class DeleteFavoriteGroupUseCaseImpl @Inject constructor(
    private val favoriteGroupsRepository: FavoriteGroupsRepository
) : DeleteFavoriteGroupUseCase {

    override suspend fun invoke(group: Group) {
        return favoriteGroupsRepository.delete(group)
    }
}
