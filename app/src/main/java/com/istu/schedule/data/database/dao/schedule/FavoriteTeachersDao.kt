package com.istu.schedule.data.database.dao.schedule

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.istu.schedule.data.database.entities.schedule.FavoriteTeacherDto

@Dao
interface FavoriteTeachersDao {

    @Query("SELECT * FROM favorite_teacher")
    suspend fun getAll(): List<FavoriteTeacherDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteTeacher: FavoriteTeacherDto)

    @Delete
    suspend fun delete(favoriteTeacher: FavoriteTeacherDto)
}
