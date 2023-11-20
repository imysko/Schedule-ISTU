package com.istu.schedule.data.api.entities.projfair

data class ProjfairFiltersState(
    val isChanged: Boolean = true,
    val statusesList: List<Int> = listOf(),
    val difficultiesList: List<Int> = listOf(),
    val specialitiesList: List<Pair<Int, String>> = listOf(),
    val skillsList: List<Pair<Int, String>> = listOf()
)
