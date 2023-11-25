package com.istu.schedule.di

import com.istu.schedule.domain.usecase.schedule.GetClassroomsListUseCase
import com.istu.schedule.domain.usecase.schedule.GetClassroomsListUseCaseImpl
import com.istu.schedule.domain.usecase.schedule.GetGroupsListUseCase
import com.istu.schedule.domain.usecase.schedule.GetGroupsListUseCaseImpl
import com.istu.schedule.domain.usecase.schedule.GetInstitutesListUseCase
import com.istu.schedule.domain.usecase.schedule.GetInstitutesListUseCaseImpl
import com.istu.schedule.domain.usecase.schedule.GetScheduleOnDayUseCase
import com.istu.schedule.domain.usecase.schedule.GetScheduleOnDayUseCaseImpl
import com.istu.schedule.domain.usecase.schedule.GetTeachersListUseCase
import com.istu.schedule.domain.usecase.schedule.GetTeachersListUseCaseImpl
import com.istu.schedule.domain.usecase.schedule.favorite.DeleteFavoriteClassroomUseCase
import com.istu.schedule.domain.usecase.schedule.favorite.DeleteFavoriteClassroomUseCaseImpl
import com.istu.schedule.domain.usecase.schedule.favorite.DeleteFavoriteGroupUseCase
import com.istu.schedule.domain.usecase.schedule.favorite.DeleteFavoriteGroupUseCaseImpl
import com.istu.schedule.domain.usecase.schedule.favorite.DeleteFavoriteTeacherUseCase
import com.istu.schedule.domain.usecase.schedule.favorite.DeleteFavoriteTeacherUseCaseImpl
import com.istu.schedule.domain.usecase.schedule.favorite.GetAllActiveFavoriteGroupUseCase
import com.istu.schedule.domain.usecase.schedule.favorite.GetAllActiveFavoriteGroupUseCaseImpl
import com.istu.schedule.domain.usecase.schedule.favorite.GetAllFavoriteClassroomUseCase
import com.istu.schedule.domain.usecase.schedule.favorite.GetAllFavoriteClassroomUseCaseImpl
import com.istu.schedule.domain.usecase.schedule.favorite.GetAllFavoriteTeacherUseCase
import com.istu.schedule.domain.usecase.schedule.favorite.GetAllFavoriteTeacherUseCaseImpl
import com.istu.schedule.domain.usecase.schedule.favorite.InsertFavoriteClassroomUseCase
import com.istu.schedule.domain.usecase.schedule.favorite.InsertFavoriteClassroomUseCaseImpl
import com.istu.schedule.domain.usecase.schedule.favorite.InsertFavoriteGroupUseCase
import com.istu.schedule.domain.usecase.schedule.favorite.InsertFavoriteGroupUseCaseImpl
import com.istu.schedule.domain.usecase.schedule.favorite.InsertFavoriteTeacherUseCase
import com.istu.schedule.domain.usecase.schedule.favorite.InsertFavoriteTeacherUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    /** UseCases for "Projfair" */

    /** UseCases for schedule */
    @Binds
    @Singleton
    internal abstract fun bindGetScheduleOnDayUseCase(
        getScheduleOnDayUseCase: GetScheduleOnDayUseCaseImpl,
    ): GetScheduleOnDayUseCase

    @Binds
    @Singleton
    internal abstract fun bindGetInstitutesListUseCase(
        getInstitutesListUseCase: GetInstitutesListUseCaseImpl,
    ): GetInstitutesListUseCase

    @Binds
    @Singleton
    internal abstract fun bindGetGroupsListUseCase(
        getGroupsListUseCase: GetGroupsListUseCaseImpl,
    ): GetGroupsListUseCase

    @Binds
    @Singleton
    internal abstract fun bindGetTeachersListUseCase(
        getTeachersListUseCase: GetTeachersListUseCaseImpl,
    ): GetTeachersListUseCase

    @Binds
    @Singleton
    internal abstract fun bindGetClassroomsListUseCase(
        getClassroomsListUseCase: GetClassroomsListUseCaseImpl,
    ): GetClassroomsListUseCase

    /** UseCases for Favorite list */
    @Binds
    @Singleton
    internal abstract fun bindGetAllActiveFavoriteGroupUseCase(
        getAllActiveFavoriteGroupUseCase: GetAllActiveFavoriteGroupUseCaseImpl,
    ): GetAllActiveFavoriteGroupUseCase

    @Binds
    @Singleton
    internal abstract fun bindGetAllFavoriteTeacherUseCase(
        getAllFavoriteTeacherUseCase: GetAllFavoriteTeacherUseCaseImpl,
    ): GetAllFavoriteTeacherUseCase

    @Binds
    @Singleton
    internal abstract fun bindGetAllFavoriteClassroomUseCase(
        getAllFavoriteClassroomUseCase: GetAllFavoriteClassroomUseCaseImpl,
    ): GetAllFavoriteClassroomUseCase

    @Binds
    @Singleton
    internal abstract fun bindInsertFavoriteGroupUseCase(
        insertFavoriteGroupUseCase: InsertFavoriteGroupUseCaseImpl,
    ): InsertFavoriteGroupUseCase

    @Binds
    @Singleton
    internal abstract fun bindInsertFavoriteTeacherUseCase(
        insertFavoriteTeacherUseCase: InsertFavoriteTeacherUseCaseImpl,
    ): InsertFavoriteTeacherUseCase

    @Binds
    @Singleton
    internal abstract fun bindInsertFavoriteClassroomUseCase(
        insertFavoriteClassroomUseCase: InsertFavoriteClassroomUseCaseImpl,
    ): InsertFavoriteClassroomUseCase

    @Binds
    @Singleton
    internal abstract fun bindDeleteFavoriteGroupUseCase(
        deleteFavoriteGroupUseCase: DeleteFavoriteGroupUseCaseImpl,
    ): DeleteFavoriteGroupUseCase

    @Binds
    @Singleton
    internal abstract fun bindDeleteFavoriteTeacherUseCase(
        deleteFavoriteTeacherUseCase: DeleteFavoriteTeacherUseCaseImpl,
    ): DeleteFavoriteTeacherUseCase

    @Binds
    @Singleton
    internal abstract fun bindDeleteFavoriteClassroomUseCase(
        deleteFavoriteClassroomUseCase: DeleteFavoriteClassroomUseCaseImpl,
    ): DeleteFavoriteClassroomUseCase
}
