package com.istu.schedule.ui.util.previewParameterProviders

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import me.progneo.projfair.domain.model.Candidate

class SampleCandidatePreviewParameterProvider : PreviewParameterProvider<Candidate> {
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
