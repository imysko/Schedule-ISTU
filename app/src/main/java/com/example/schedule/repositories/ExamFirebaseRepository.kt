package com.example.schedule.repositories

import androidx.lifecycle.MutableLiveData
import com.example.schedule.entities.Exam
import com.google.firebase.database.*

class ExamFirebaseRepository {
    private lateinit var _reference: DatabaseReference

    private val _listRecords = MutableLiveData<ArrayList<Exam>>().apply {
        value = ArrayList()
    }
    val listRecords: MutableLiveData<ArrayList<Exam>> = _listRecords

    private fun fetchingRecords() {
        _reference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list: ArrayList<Exam> = ArrayList()
                for (record in snapshot.children) {
                    list.add(record.getValue(Exam::class.java)!!)
                }

                _listRecords.value = list
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