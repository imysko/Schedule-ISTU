package me.progneo.projfair.domain.model

data class FiltersState(
    val isChanged: Boolean = true,
    val statusesList: List<Int> = listOf(),
    val difficultiesList: List<Int> = listOf(),
    val specialitiesList: List<Pair<Int, String>> = listOf(),
    val skillsList: List<Pair<Int, String>> = listOf()
)
