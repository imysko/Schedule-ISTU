package com.istu.schedule.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.istu.schedule.data.database.dao.schedule.FavoriteClassroomsDao
import com.istu.schedule.data.database.dao.schedule.FavoriteGroupsDao
import com.istu.schedule.data.database.dao.schedule.FavoriteTeachersDao
import com.istu.schedule.data.database.entities.schedule.FavoriteClassroomDto
import com.istu.schedule.data.database.entities.schedule.FavoriteGroupDto
import com.istu.schedule.data.database.entities.schedule.FavoriteTeacherDto

@Database(
    entities = [
        FavoriteGroupDto::class,
        FavoriteTeacherDto::class,
        FavoriteClassroomDto::class,
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(DatabaseConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteGroupDao(): FavoriteGroupsDao

    abstract fun favoriteTeacherDao(): FavoriteTeachersDao

    abstract fun favoriteClassroomDao(): FavoriteClassroomsDao
}