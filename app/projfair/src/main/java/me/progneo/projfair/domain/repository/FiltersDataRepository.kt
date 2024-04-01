package me.progneo.projfair.domain.repository

import me.progneo.projfair.domain.model.Skill
import me.progneo.projfair.domain.model.SkillCategory
import me.progneo.projfair.domain.model.Speciality

interface FiltersDataRepository {
    suspend fun getSkills(token: String?): Result<List<Skill>>
    suspend fun getSpecialities(token: String?): Result<List<Speciality>>
    suspend fun getSkillsCategories(token: String?): Result<List<SkillCategory>>
}
