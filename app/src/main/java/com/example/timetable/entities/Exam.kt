package com.example.timetable.entities

data class Exam(
    val date: String,
    override val time: String,
    override val name: String,
    override val teacher: String,
    override val classroom: String
) : Record() {
    // for serialize data from firebase to class
    constructor() : this("", "", "", "", "")
}