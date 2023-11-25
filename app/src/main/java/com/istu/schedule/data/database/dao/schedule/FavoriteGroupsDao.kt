package com.istu.schedule.data.database.dao.schedule

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.istu.schedule.data.database.entities.schedule.FavoriteGroupDto

@Dao
interface FavoriteGroupsDao {

    @Query("SELECT * FROM favorite_group")
    suspend fun getAll(): List<FavoriteGroupDto>

    @Query("SELECT * FROM favorite_group WHERE is_active = $TRUE ORDER BY name")
    suspend fun getActive(): List<FavoriteGroupDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteGroup: FavoriteGroupDto)

    @Delete
    suspend fun delete(favoriteGroup: FavoriteGroupDto)

    companion object {
        const val FALSE = 0
        const val TRUE = 1
    }
}
