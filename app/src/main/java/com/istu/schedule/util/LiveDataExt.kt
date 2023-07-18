package com.istu.schedule.util

import androidx.lifecycle.MutableLiveData

inline fun <reified T> MutableLiveData<List<T>>.addNewItem(item: T) {
    val newList = mutableListOf<T>()
    this.value?.let { newList.addAll(it) }
    newList.add(item)
    this.value = newList
}

inline fun <reified T> MutableLiveData<List<T>>.addNewItemAsync(item: T) {
    val newList = mutableListOf<T>()
    this.value?.let { newList.addAll(it) }
    newList.add(item)
    this.postValue(newList)
}
