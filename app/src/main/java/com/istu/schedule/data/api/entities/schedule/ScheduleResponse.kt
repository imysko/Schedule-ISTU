package com.istu.schedule.data.api.entities.schedule

import com.google.gson.annotations.SerializedName

data class ScheduleResponse(
    @SerializedName("scheduleId")
    val scheduleId: Int,
    @SerializedName("groupsVerbose")
    val groupsVerbose: String,
    @SerializedName("groups")
    val groups: List<GroupResponse>?,
    @SerializedName("teachersVerbose")
    val teachersVerbose: String,
    @SerializedName("teachers")
    val teachers: List<TeacherResponse>?,
    @SerializedName("classroomId")
    val classroomId: Int?,
    @SerializedName("classroom")
    val classroom: ClassroomResponse?,
    @SerializedName("classroomVerbose")
    val classroomVerbose: String,
    @SerializedName("disciplineId")
    val disciplineId: Int,
    @SerializedName("discipline")
    val discipline: DisciplineResponse?,
    @SerializedName("otherDisciplineId")
    val otherDisciplineId: Int,
    @SerializedName("otherDiscipline")
    val otherDiscipline: OtherDisciplineResponse?,
    @SerializedName("queryId")
    val queryId: Int?,
    @SerializedName("query")
    val query: QueryResponse?,
    @SerializedName("disciplineVerbose")
    val disciplineVerbose: String,
    @SerializedName("lessonId")
    val lessonId: Int,
    @SerializedName("lessonTime")
    val lessonTime: LessonTimeResponse,
    @SerializedName("subgroup")
    val subgroup: Int,
    @SerializedName("lessonType")
    val lessonType: LessonTypeResponse?,
    @SerializedName("scheduleType")
    val scheduleType: String?,
    @SerializedName("date")
    val date: String
)
