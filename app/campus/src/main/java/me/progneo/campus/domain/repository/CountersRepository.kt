package me.progneo.campus.domain.repository

import me.progneo.campus.domain.entities.Counters

interface CountersRepository {

    suspend fun getCounters(): Result<Counters>
}
