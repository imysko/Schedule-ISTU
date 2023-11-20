package com.istu.schedule.domain.repository.projfair

import com.istu.schedule.domain.entities.projfair.Skill
import com.istu.schedule.domain.entities.projfair.SkillCategory
import com.istu.schedule.domain.entities.projfair.Speciality

interface FiltersDataRepository {
    suspend fun getSkills(token: String?): Result<List<Skill>>
    suspend fun getSpecialities(token: String?): Result<List<Speciality>>
    suspend fun getSkillsCategories(token: String?): Result<List<SkillCategory>>
}
