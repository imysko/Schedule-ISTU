package com.istu.schedule.data.model

data class ProjfairFiltersState(
    val statusesList: List<Int> = listOf(),
    val difficultiesList: List<Int> = listOf(),
    val specialitiesList: List<Pair<Int, String>> = listOf(),
    val skillsList: List<Pair<Int, String>> = listOf(),
)
