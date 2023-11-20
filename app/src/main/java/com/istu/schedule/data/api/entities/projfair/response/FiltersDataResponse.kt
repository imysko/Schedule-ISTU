package com.istu.schedule.data.api.entities.projfair.response

import com.google.gson.annotations.SerializedName
import com.istu.schedule.domain.entities.projfair.Skill
import com.istu.schedule.domain.entities.projfair.SkillCategory
import com.istu.schedule.domain.entities.projfair.Speciality

data class FiltersDataResponse(
    @SerializedName("skills")
    val skillsList: List<Skill>?,
    @SerializedName("specialties")
    val specialitiesList: List<Speciality>?,
    @SerializedName("skillCategories")
    val skillCategoriesList: List<SkillCategory>?,
)
