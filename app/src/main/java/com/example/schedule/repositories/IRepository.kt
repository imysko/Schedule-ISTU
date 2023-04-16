package com.example.schedule.repositories

import androidx.lifecycle.MutableLiveData
import com.example.schedule.entities.Record

interface IRepository {
     val listRecords: MutableLiveData<ArrayList<Record>>

     fun updateQuery(institute: String, group: String)
}