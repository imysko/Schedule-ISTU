package com.istu.schedule.ui.util.previewParameterProviders

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import java.util.Date
import me.progneo.projfair.domain.model.Candidate
import me.progneo.projfair.domain.model.Department
import me.progneo.projfair.domain.model.Institute
import me.progneo.projfair.domain.model.Participation
import me.progneo.projfair.domain.model.Project
import me.progneo.projfair.domain.model.ProjectState
import me.progneo.projfair.domain.model.ProjectSupervisor
import me.progneo.projfair.domain.model.ProjectSupervisorRole
import me.progneo.projfair.domain.model.ProjectType
import me.progneo.projfair.domain.model.State
import me.progneo.projfair.domain.model.Supervisor

class SampleProjectPreviewParameterProvider : PreviewParameterProvider<Project> {
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
            supervisors = listOf(
                ProjectSupervisor(
                    id = 109,
                    roles = listOf(
                        ProjectSupervisorRole(
                            id = 1,
                            name = "Создатель задания"
                        ),
                        ProjectSupervisorRole(
                            id = 2,
                            name = "Руководитель"
                        )
                    ),
                    supervisor = Supervisor(
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
                )
            ),
            skills = listOf(),
            specialities = listOf(),
            projectSpecialities = listOf(),
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
            participations = listOf(
                Participation(
                    id = 0,
                    priority = 1,
                    projectId = 307,
                    candidateId = 1,
                    createdAt = Date(),
                    updatedAt = Date(),
                    candidate = Candidate(
                        id = 1,
                        fio = "Иванов Иван Иванович",
                        about = "",
                        email = "ivanov@yandex.ru",
                        courseBookNumber = "1",
                        phone = "+7 (912) 345-67-89",
                        course = 3,
                        trainingGroup = "ИСТб-20-3",
                        canSendParticipations = 1
                    ),
                    state = State(
                        id = 2,
                        state = "me.progneo.projfair.domain.model.State"
                    ),
                    project = Project(
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
                        supervisors = listOf(
                            ProjectSupervisor(
                                id = 109,
                                roles = listOf(
                                    ProjectSupervisorRole(
                                        id = 1,
                                        name = "Создатель задания"
                                    ),
                                    ProjectSupervisorRole(
                                        id = 2,
                                        name = "Руководитель"
                                    )
                                ),
                                supervisor = Supervisor(
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
                            )
                        ),
                        skills = listOf(),
                        specialities = listOf(),
                        projectSpecialities = listOf(),
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
                        participations = listOf(),
                        createdAt = Date(),
                        updatedAt = Date()
                    )
                )
            ),
            createdAt = Date(),
            updatedAt = Date()
        )
    )
}
