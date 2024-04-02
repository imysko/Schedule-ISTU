package com.istu.schedule.data.database.entities.schedule

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "favorite_classroom")
data class FavoriteClassroomDto(
    @PrimaryKey
    val id: Int,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("last_update_time")
    val lastUpdateTime: LocalDateTime = LocalDateTime.now()
)
