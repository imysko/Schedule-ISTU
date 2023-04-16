package com.example.schedule.repositories

import androidx.lifecycle.MutableLiveData
import com.example.schedule.entities.Lesson
import com.google.firebase.database.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ScheduleFirebaseRepository {
    private lateinit var _reference: DatabaseReference

    private val _listRecords = MutableLiveData<ArrayList<Lesson>>().apply {
        value = ArrayList()
    }
    val listRecords: MutableLiveData<ArrayList<Lesson>> = _listRecords

    private fun fetchingRecords() {
        _reference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list: ArrayList<Lesson> = ArrayList()
                for (record in snapshot.children) {
                    val formatter = DateTimeFormatter.ofPattern("d-MM-yyyy H:mm")

                    val lesson = record.getValue(Lesson::class.java)!!
                    lesson.dateTime = LocalDateTime.parse("${snapshot.key!!} ${lesson.time}", formatter)

                    list.add(lesson)
                }

                _listRecords.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    fun updateQuery(date: LocalDate, institute: String, group: String) {
        val formatter = DateTimeFormatter.ofPattern("d-MM-yyyy")
        _reference = FirebaseDatabase.getInstance().getReference(
            "/${institute}/${group}/Days/${formatter.format(date)}")
        fetchingRecords()
    }
}