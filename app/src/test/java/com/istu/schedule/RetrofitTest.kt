package com.istu.schedule

import com.istu.schedule.data.enums.LessonType
import com.istu.schedule.data.enums.ScheduleType
import com.istu.schedule.data.repository.projfair.ProjectsRepositoryImpl
import com.istu.schedule.data.repository.schedule.InstitutesRepositoryImpl
import com.istu.schedule.data.repository.schedule.ScheduleRepositoryImpl
import com.istu.schedule.data.service.projfair.ProjectsService
import com.istu.schedule.data.service.schedule.InstitutesService
import com.istu.schedule.data.service.schedule.ScheduleService
import com.istu.schedule.domain.usecase.projfair.GetProjectUseCase
import com.istu.schedule.domain.usecase.projfair.GetProjectsListUseCase
import com.istu.schedule.domain.usecase.schedule.GetInstitutesListUseCase
import com.istu.schedule.domain.usecase.schedule.GetScheduleOnDayUseCase
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitTest {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .addInterceptor {
            val original = it.request()
            val newRequestBuilder = original.newBuilder()
            newRequestBuilder.addHeader("Content-Type", "application/json")
            newRequestBuilder.addHeader("Accept", "application/json")
            it.proceed(newRequestBuilder.build())
        }
        .addInterceptor {
            val original = it.request()
            val newUrl = original.url
                .newBuilder()
                .build()
            val newRequest = original
                .newBuilder()
                .url(newUrl)
                .build()

            it.proceed(newRequest)
        }
        .callTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .build()

    private val projfairRetrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl("https://projfair.istu.edu/")
        .build()

    private val scheduleRetrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl("https://schedule-api.imysko.ru/")
        .build()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getProjectTest() = runTest {
        val repository =
            ProjectsRepositoryImpl(projfairRetrofit.create(ProjectsService::class.java))
        val getProjectUseCase = GetProjectUseCase(repository)

        val response = getProjectUseCase.getProject(307)

        assert(response.getOrNull()?.id == 307)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getProjectsTest() = runTest {
        val repository =
            ProjectsRepositoryImpl(projfairRetrofit.create(ProjectsService::class.java))
        val getProjectsListUseCase = GetProjectsListUseCase(repository)

        val response = getProjectsListUseCase.getProjectList(title = "Ярмарка проектов", page = 1)

        assert(response.getOrNull() != null)
        assert(
            response.getOrNull()?.all { project ->
                project.title.contains("Ярмарка проектов")
            }!!
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getInstitutesTest() = runTest {
        val repository =
            InstitutesRepositoryImpl(scheduleRetrofit.create(InstitutesService::class.java))
        val getInstitutesListUseCase = GetInstitutesListUseCase(repository)

        val response = getInstitutesListUseCase.getInstitutesList()

        val expectedList = listOf(
            "Аспирантура",
            "Байкальский институт БРИКС",
            "Институт авиамашиностроения и транспорта",
            "Институт архитектуры, строительства и дизайна",
            "Институт высоких технологий",
            "Институт заочно-вечернего обучения",
            "Институт информационных технологий и анализа данных",
            "Институт лингвистики и межкультурной коммуникации",
            "Институт недропользования",
            "Институт экономики, управления и права",
            "Институт энергетики",
            "Сибирская Школа Геонаук"
        )

        assert(response.getOrNull()?.all { expectedList.contains(it.instituteTitle) } ?: false)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getScheduleTest() = runTest {
        val repository =
            ScheduleRepositoryImpl(scheduleRetrofit.create(ScheduleService::class.java))
        val getScheduleOnDayUseCase = GetScheduleOnDayUseCase(repository)

        val response = getScheduleOnDayUseCase.getScheduleOnDay(
            scheduleType = ScheduleType.BY_GROUP,
            id = 464443,
            dateString = "2023-05-10"
        )

        val expectedDate = "2023-05-10"
        val expectedGroup = "ИСТб-20-3"
        val expectedSecondLessonStartTime = "10:00"
        val expectedSecondLessonType = LessonType.LABORATORY_WORK
        val expectedSecondLessonName = "Технология разработки программных комплексов"
        val expectedSecondLessonTeacherShortName = "Бахвалова З.А."
        val expectedSecondLessonClassroomName = "В-208"
        val expectedSecondLessonSubgroup = 2

        val secondLesson = response.getOrNull()?.first()?.lessons?.get(1)?.schedules?.get(1)!!

        assert(secondLesson.date == expectedDate)
        secondLesson.groups?.any { it.name == expectedGroup }?.let { assert(it) }
        assert(secondLesson.lessonTime.begTime == expectedSecondLessonStartTime)
        assert(secondLesson.lessonType == expectedSecondLessonType)
        assert(secondLesson.disciplineVerbose == expectedSecondLessonName)
        secondLesson.teachers?.let {
            assert(it.any { teacher -> teacher.shortname == expectedSecondLessonTeacherShortName })
        }
        assert(secondLesson.classroomVerbose == expectedSecondLessonClassroomName)
        assert(secondLesson.subgroup == expectedSecondLessonSubgroup)
    }
}
