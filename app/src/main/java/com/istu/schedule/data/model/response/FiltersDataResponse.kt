package com.istu.schedule.data.model.response

import com.google.gson.annotations.SerializedName
import com.istu.schedule.domain.model.projfair.Skill
import com.istu.schedule.domain.model.projfair.SkillCategory
import com.istu.schedule.domain.model.projfair.Speciality

data class FiltersDataResponse(
    @SerializedName("skills")
    val skillsList: List<Skill>?,
    @SerializedName("specialties")
    val specialitiesList: List<Speciality>?,
    @SerializedName("skillCategories")
    val skillCategoriesList: List<SkillCategory>?,
)
