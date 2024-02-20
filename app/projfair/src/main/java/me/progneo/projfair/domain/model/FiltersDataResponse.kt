package me.progneo.projfair.domain.model

import com.google.gson.annotations.SerializedName

data class FiltersDataResponse(
    @SerializedName("skills")
    val skillsList: List<Skill>?,
    @SerializedName("specialties")
    val specialitiesList: List<Speciality>?,
    @SerializedName("skillCategories")
    val skillCategoriesList: List<SkillCategory>?
)
