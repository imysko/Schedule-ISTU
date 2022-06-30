package com.example.timetable.repositories

import androidx.lifecycle.MutableLiveData
import com.example.timetable.entities.Record

interface IRepository {
     val listRecords: MutableLiveData<ArrayList<Record>>

     fun updateQuery(institute: String, group: String)
}