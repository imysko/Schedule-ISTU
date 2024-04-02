package com.istu.schedule.di

import android.content.Context
import androidx.room.Room
import com.istu.schedule.data.database.AppDatabase
import com.istu.schedule.data.database.dao.schedule.FavoriteClassroomsDao
import com.istu.schedule.data.database.dao.schedule.FavoriteGroupsDao
import com.istu.schedule.data.database.dao.schedule.FavoriteTeachersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Named("AppDatabaseName")
    fun provideAppDatabaseName() = "app_database"

    @Provides
    @Named("AppDatabase")
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        @Named("AppDatabaseName") databaseName: String
    ): AppDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = databaseName
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteGroupDao(
        @Named("AppDatabase") appDatabase: AppDatabase
    ): FavoriteGroupsDao {
        return appDatabase.favoriteGroupDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteTeacherDao(
        @Named("AppDatabase") appDatabase: AppDatabase
    ): FavoriteTeachersDao {
        return appDatabase.favoriteTeacherDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteClassroomDao(
        @Named("AppDatabase") appDatabase: AppDatabase
    ): FavoriteClassroomsDao {
        return appDatabase.favoriteClassroomDao()
    }
}
