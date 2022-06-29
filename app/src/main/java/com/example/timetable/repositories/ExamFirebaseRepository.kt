package com.example.timetable.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.timetable.objects.Exam
import com.google.firebase.database.*

class ExamFirebaseRepository {
    private lateinit var _reference: DatabaseReference

    private val _listRecords = MutableLiveData<ArrayList<Exam>>().apply {
        value = ArrayList()
    }
    val listRecords: MutableLiveData<ArrayList<Exam>> = _listRecords

    private val _examListIsEmpty = MutableLiveData(true)
    var examListIsEmpty: MutableLiveData<Boolean> = _examListIsEmpty

    private fun fetchingRecords() {
        _reference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list: ArrayList<Exam> = ArrayList()
                for (record in snapshot.children) {
                    list.add(record.getValue(Exam::class.java)!!)
                }

                _listRecords.value = list
                _examListIsEmpty.value = list.isEmpty()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    fun updateQuery(institute: String, group: String) {
        _reference = FirebaseDatabase.getInstance().getReference("/${institute}/${group}/Exams/")
        fetchingRecords()
    }
}