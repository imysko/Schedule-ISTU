package com.example.schedule.ui.fragments.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {

    val list: MutableList<String> = mutableListOf()

    private val _institutes = MutableLiveData<ArrayList<String>>().apply {
        // val databaseReference = FirebaseDatabase.getInstance().getReference("/")
//
        // databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
        //     override fun onDataChange(snapshot: DataSnapshot) {
        //         list.clear()
        //         for (child in snapshot.children) {
        //             child.key?.let { value!!.add(it) }
        //             child.key?.let { list.add(it) }
        //         }
        //     }
//
        //     override fun onCancelled(error: DatabaseError) {
        //         TODO("Not yet implemented")
        //     }
        // })
    }
    val institutes: LiveData<ArrayList<String>> = _institutes

    private val _groups = MutableLiveData<ArrayList<String>>()
    val groups: LiveData<ArrayList<String>> = _groups

    private val _selectedInstitute = MutableLiveData<String>()
    private val _selectedGroup = MutableLiveData<String>()

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}