package me.progneo.projfair.domain.repository

import me.progneo.projfair.domain.model.Skill
import me.progneo.projfair.domain.model.SkillCategory
import me.progneo.projfair.domain.model.Speciality

interface FiltersDataRepository {
    suspend fun getSkills(): Result<List<Skill>>
    suspend fun getSpecialities(): Result<List<Speciality>>
    suspend fun getSkillsCategories(): Result<List<SkillCategory>>
}
