package com.istu.schedule.domain.model.projfair

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.google.gson.annotations.SerializedName

data class Candidate(
    val id: Int,
    val fio: String,
    val about: String,
    val email: String,
    @SerializedName("numz")
    val courseBookNumber: String,
    val phone: String,
    val course: Int,
    @SerializedName("training_group")
    val trainingGroup: String,
    val canSendParticipations: Int
)

class SampleCandidateProvider : PreviewParameterProvider<Candidate> {
    override val values = sequenceOf(
        Candidate(
            id = 1,
            fio = "Иванов Иван Иванович",
            about = "",
            email = "ivanov@yandex.ru",
            courseBookNumber = "1",
            phone = "+7 (912) 345-67-89",
            course = 3,
            trainingGroup = "ИСТб-20-3",
            canSendParticipations = 1
        )
    )
}
