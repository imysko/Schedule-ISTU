package com.istu.schedule.domain.model.projfair

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.google.gson.annotations.SerializedName
import java.util.Date

data class Project(
    val id: Int,
    val prevProjectId: Int?,
    val title: String,
    val places: Int,
    val goal: String,
    val description: String,
    val difficulty: Int,
    @SerializedName("date_start")
    val dateStart: Date,
    @SerializedName("date_end")
    val dateEnd: Date,
    val requirements: String,
    @SerializedName("additional_inf")
    val additionalInfo: String,
    @SerializedName("product_result")
    val productResult: String,
    @SerializedName("study_result")
    val studyResult: String,
    val customer: String,
    val supervisor: List<Supervisor>,
    val projectSupervisors: List<ProjectSupervisor>,
    val skills: List<Skill>,
    val specialities: List<Speciality>,
    @SerializedName("project_specialities")
    val projectSpecialities: List<ProjectSpeciality>,
    val supervisorsNames: String,
    val state: ProjectState,
    val department: Department,
    val type: ProjectType,
    val participations: List<Participation>,
    @SerializedName("created_at")
    val createdAt: Date,
    @SerializedName("updated_at")
    val updatedAt: Date
)

class SampleProjectProvider : PreviewParameterProvider<Project> {
    override val values = sequenceOf(
        Project(
            id = 307,
            prevProjectId = 4,
            title = "Развитие веб-платформы «Ярмарка проектов ИРНИТУ»",
            places = 16,
            goal = "Создание решения для поддержки процесса распределения студентов ИРНИТУ в рамках проектной деятельности",
            description = "Данный проект основывается на результатах проекта «Развитие веб-платформы “Ярмарка проектов” института ИТиАД»\r\nВ настоящее время имеется следующий задел по проекту: разработано веб-приложение позволяющее студентам просматривать карточки проектов, осуществлять сбор заявок от студентов на участие и автоматически распределять студентов по проектам с учетом их предпочтений и принадлежности к тому или иному направлению подготовки\r\nВ рамках данного проекта планируется доработать имеющуюся систему для поддержки процесса распределения студентов ИРНИТУ по проектам, для чего необходимо реализовать интерфейс руководителя центра проектного обучения, а также разработать механизмы дальнейшей интеграции с «АИС Университет»",
            difficulty = 2,
            dateStart = Date(),
            dateEnd = Date(),
            requirements = "",
            additionalInfo = "",
            productResult = "Реализация следующих функций: добавление отзыва руководителя на работу участника проекта,  интерфейс руководителя центра проектного обучения для загрузки аннотаций проектов в систему, ручного перераспределения студентов по проектам, запуска механизма автоматического распределения студентов, в том числе «молчунов» (не подавших заявки на проекты), выгрузки списков распределения студентов по проектам.",
            studyResult = "Знания: принципы работы веб-сайта, основы веб-программирования, ORM-фреймворки, нотация Чена, стандарты описания UML, IDEF0, RDF\r\nУмения: читать и составлять техническую документацию с использованием стандартов UML, IDEF0, RDF, моделирование данных, составление SQL-запросов, \r\nНавыки: работа с СУБД MySQL, администрирование Linux, работа с Figma, разработка JavaScript скриптов, разработка php-скриптов, работы с Laravel",
            customer = "Центр проектного обучения",
            supervisor = listOf(
                Supervisor(
                    id = 2,
                    fio = "Аршинский Вадим Леонидович",
                    email = "arshinskyv@mail.ru",
                    about = "Руководитель центра программной инженерии",
                    position = "251100 Центр программной инженерии",
                    department = Department(
                        id = 59,
                        name = "Информационных технологий и анализа данных",
                        institute = Institute(
                            id = 4,
                            name = "Институт информационных технологий и анализа данных"
                        )
                    )
                )
            ),
            skills = listOf(),
            specialities = listOf(),
            projectSpecialities = listOf(),
            supervisorsNames = "Аршинский Вадим Леонидович, Серышева Ирина Анатольевна",
            state = ProjectState(
                id = 2,
                state = "Активный"
            ),
            department = Department(
                id = 59,
                name = "Информационных технологий и анализа данных",
                institute = Institute(
                    id = 4,
                    name = "Институт информационных технологий и анализа данных"
                )
            ),
            type = ProjectType(
                id = 1,
                type = "Прикладной"
            ),
            projectSupervisors = listOf(),
            participations = listOf(),
            createdAt = Date(),
            updatedAt = Date()
        )
    )
}
