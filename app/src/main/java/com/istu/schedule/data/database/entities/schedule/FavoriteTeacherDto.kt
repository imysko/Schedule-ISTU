package com.istu.schedule.data.database.entities.schedule

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "favorite_teacher")
data class FavoriteTeacherDto(
    @PrimaryKey
    val id: Int,
    @ColumnInfo("full_name")
    val fullName: String,
    @ColumnInfo("short_name")
    val shortName: String,
    @ColumnInfo("last_update_time")
    val lastUpdateTime: LocalDateTime = LocalDateTime.now()
)
