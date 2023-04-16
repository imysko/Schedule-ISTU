package com.example.schedule.entities

import java.time.LocalDateTime

data class Lesson(
    val group: String,
    val type: String,
    var dateTime: LocalDateTime,
    override val time: String,
    override val name: String,
    override val teacher: String,
    override val classroom: String
) : Record() {
    // for serialize data from firebase to class
    constructor() : this("", "type", LocalDateTime.now(),"", "", "", "")
}
