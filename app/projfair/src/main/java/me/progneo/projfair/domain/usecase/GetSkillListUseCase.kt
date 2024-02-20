package me.progneo.projfair.domain.usecase

import javax.inject.Inject
import me.progneo.projfair.domain.model.Skill
import me.progneo.projfair.domain.repository.FiltersDataRepository

class GetSkillListUseCase @Inject constructor(
    private val filterDataRepository: FiltersDataRepository
) {

    suspend operator fun invoke(token: String): Result<List<Skill>> {
        return filterDataRepository.getSkills(token)
    }
}
