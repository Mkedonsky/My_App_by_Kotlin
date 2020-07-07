package ru.mkedonsky.myappbykotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private  val model = Model()
    private val viewStateLiveData = MutableLiveData<String>()

    init {
        model.getStringLiveData().observeForever{str ->
            viewStateLiveData.value = str

        }
    }
    fun viewState():LiveData<String> = viewStateLiveData
}