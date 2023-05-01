package com.istu.schedule.util

import androidx.lifecycle.MutableLiveData

inline fun <reified T> MutableLiveData<MutableList<T>>.addNewItem(item: T) {
    val newList = mutableListOf<T>()
    this.value?.let { newList.addAll(it) }
    newList.add(item)
    this.value = newList
}

inline fun <reified T> MutableLiveData<MutableList<T>>.addFrontItem(item: T) {
    val newList = mutableListOf<T>()
    newList.add(item)
    this.value?.let { newList.addAll(it) }
    this.value = newList
}

inline fun <reified T> MutableLiveData<MutableList<T>>.updateItemAt(item: T, index: Int) {
    val newList = mutableListOf<T>()
    this.value?.let { newList.addAll(it) }
    newList[index] = item
    this.value = newList
}

inline fun <reified T> MutableLiveData<MutableList<T>>.insertItem(item: T, index: Int) {
    val newList = mutableListOf<T>()
    this.value?.let { newList.addAll(it) }
    newList.add(index, item)
    this.value = newList
}

inline fun <reified T> MutableLiveData<MutableList<T>>.removeItem(item: T) {
    val newList = mutableListOf<T>()
    this.value?.let { newList.addAll(it) }
    newList.remove(item)
    this.value = newList
}
