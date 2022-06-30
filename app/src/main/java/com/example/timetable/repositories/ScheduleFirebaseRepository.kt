package com.example.timetable.repositories

import androidx.lifecycle.MutableLiveData
import com.example.timetable.entities.Lesson
import com.google.firebase.database.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ScheduleFirebaseRepository {
    private lateinit var _reference: DatabaseReference

    private val _listRecords = MutableLiveData<ArrayList<Lesson>>().apply {
        value = ArrayList()
    }
    val listRecords: MutableLiveData<ArrayList<Lesson>> = _listRecords

    private val _listIsEmpty = MutableLiveData(true)
    var listIsEmpty: MutableLiveData<Boolean> = _listIsEmpty

    private fun fetchingRecords() {
        _reference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list: ArrayList<Lesson> = ArrayList()
                for (record in snapshot.children) {
                    list.add(record.getValue(Lesson::class.java)!!)
                }

                _listRecords.value = list
                _listIsEmpty.value = list.isEmpty()
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