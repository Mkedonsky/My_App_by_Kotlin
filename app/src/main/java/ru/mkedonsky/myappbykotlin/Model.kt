package ru.mkedonsky.myappbykotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Model {
    private val  stringLiveData = MutableLiveData<String>()

    init {
        stringLiveData.value = "Hello World!"
    }
    fun getStringLiveData():LiveData<String> = stringLiveData
}