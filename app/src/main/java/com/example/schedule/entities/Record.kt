package com.example.schedule.entities

abstract class Record {
    abstract val time: String
    abstract val name: String
    abstract val teacher: String
    abstract val classroom: String
}