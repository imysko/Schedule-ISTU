package com.istu.schedule.domain.usecase.projfair

import com.istu.schedule.domain.model.projfair.Skill
import com.istu.schedule.domain.repository.projfair.FiltersDataRepository
import javax.inject.Inject

class GetSkillsListUseCase @Inject constructor(
    private val filtersDataRepository: FiltersDataRepository,
) {

    suspend fun getSkillsListList(token: String): Result<List<Skill>> {
        return filtersDataRepository.getSkills(token)
    }
}
