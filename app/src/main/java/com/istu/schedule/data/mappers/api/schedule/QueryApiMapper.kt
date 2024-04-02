package com.istu.schedule.data.mappers.api.schedule

import com.istu.schedule.data.api.entities.schedule.QueryResponse
import com.istu.schedule.domain.entities.schedule.Query

fun QueryResponse.mapToDomain(): Query = this.let { from ->
    return Query(
        queryId = from.queryId,
        description = from.description
    )
}
