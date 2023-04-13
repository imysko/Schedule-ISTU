package com.istu.schedule.data.repository.projfair

import com.istu.schedule.data.model.RequestException
import com.istu.schedule.data.service.projfair.FiltersDataService
import com.istu.schedule.domain.model.projfair.Skill
import com.istu.schedule.domain.model.projfair.SkillCategory
import com.istu.schedule.domain.model.projfair.Speciality
import com.istu.schedule.domain.repository.projfair.FiltersDataRepository
import java.net.HttpURLConnection
import javax.inject.Inject

class FiltersDataRepositoryImpl @Inject constructor(
    private val filtersDataService: FiltersDataService,
) : FiltersDataRepository {

    private val cachedSkillsList: MutableList<Skill> = mutableListOf()
    private val cachedSpecialitiesList: MutableList<Speciality> = mutableListOf()
    private val cachedSkillCategoriesList: MutableList<SkillCategory> = mutableListOf()

    override suspend fun getSkills(token: String?): Result<List<Skill>> {
        if (cachedSkillsList.isNotEmpty()) {
            return Result.success(cachedSkillsList)
        }

        val apiResponse = filtersDataService.getSkills(token ?: "").body()
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
                message = "An error occurred!",
            ),
        )
    }

    override suspend fun getSpecialities(token: String?): Result<List<Speciality>> {
        if (cachedSpecialitiesList.isNotEmpty()) {
            return Result.success(cachedSpecialitiesList)
        }

        val apiResponse = filtersDataService.getSkills(token ?: "").body()
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
                message = "An error occurred!",
            ),
        )
    }

    override suspend fun getSkillsCategories(token: String?): Result<List<SkillCategory>> {
        if (cachedSkillCategoriesList.isNotEmpty()) {
            return Result.success(cachedSkillCategoriesList)
        }

        val apiResponse = filtersDataService.getSkills(token ?: "").body()
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
                message = "An error occurred!",
            ),
        )
    }
}
