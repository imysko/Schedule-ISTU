package com.istu.schedule.data.database.dao.schedule

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.istu.schedule.data.database.entities.schedule.FavoriteClassroomDto

@Dao
interface FavoriteClassroomsDao {

    @Query("SELECT * FROM favorite_classroom")
    suspend fun getAll(): List<FavoriteClassroomDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteClassroom: FavoriteClassroomDto)

    @Delete
    suspend fun delete(favoriteClassroom: FavoriteClassroomDto)
}
