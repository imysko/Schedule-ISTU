package me.progneo.projfair.data.api.repository

import java.net.HttpURLConnection
import javax.inject.Inject
import me.progneo.projfair.data.api.service.FiltersDataService
import me.progneo.projfair.data.exception.RequestException
import me.progneo.projfair.domain.model.Skill
import me.progneo.projfair.domain.model.SkillCategory
import me.progneo.projfair.domain.model.Speciality
import me.progneo.projfair.domain.repository.FiltersDataRepository

internal class FiltersDataRepositoryImpl @Inject constructor(
    private val filtersDataService: FiltersDataService
) : FiltersDataRepository {

    private val cachedSkillsList: MutableList<Skill> = mutableListOf()
    private val cachedSpecialitiesList: MutableList<Speciality> = mutableListOf()
    private val cachedSkillCategoriesList: MutableList<SkillCategory> = mutableListOf()

    override suspend fun getSkills(): Result<List<Skill>> {
        if (cachedSkillsList.isNotEmpty()) {
            return Result.success(cachedSkillsList)
        }

        val apiResponse = filtersDataService.getSkills().body()
        if (apiResponse?.skillsList != null) {
            val newsList = apiResponse.skillsList
            cachedSkillsList.let { list ->
                list.addAll(newsList)
                list.distinctBy { it.name }
                return Result.success(newsList)
            }
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!"
            )
        )
    }

    override suspend fun getSpecialities(): Result<List<Speciality>> {
        if (cachedSpecialitiesList.isNotEmpty()) {
            return Result.success(cachedSpecialitiesList)
        }

        val apiResponse = filtersDataService.getSkills().body()
        if (apiResponse?.specialitiesList != null) {
            val newsList = apiResponse.specialitiesList
            cachedSpecialitiesList.let { list ->
                list.addAll(newsList)
                list.distinctBy { it.name }
                return Result.success(newsList)
            }
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!"
            )
        )
    }

    override suspend fun getSkillsCategories(): Result<List<SkillCategory>> {
        if (cachedSkillCategoriesList.isNotEmpty()) {
            return Result.success(cachedSkillCategoriesList)
        }

        val apiResponse = filtersDataService.getSkills().body()
        if (apiResponse?.specialitiesList != null) {
            val newsList = apiResponse.specialitiesList
            cachedSpecialitiesList.let { list ->
                list.addAll(newsList)
                list.distinctBy { it.name }
            }
        }
        if (apiResponse?.skillsList != null) {
            val newsList = apiResponse.skillsList
            cachedSkillsList.let { list ->
                list.addAll(newsList)
                list.distinctBy { it.name }
            }
        }
        if (apiResponse?.skillCategoriesList != null) {
            val newsList = apiResponse.skillCategoriesList
            cachedSkillCategoriesList.let { list ->
                list.addAll(newsList)
                list.distinctBy { it.id }
                return Result.success(newsList)
            }
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!"
            )
        )
    }
}
