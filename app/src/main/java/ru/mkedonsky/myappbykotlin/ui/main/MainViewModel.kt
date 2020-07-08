package ru.mkedonsky.myappbykotlin.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.mkedonsky.myappbykotlin.data.NotesRepository

class MainViewModel : ViewModel() {

    private val viewStateLiveData = MutableLiveData<MainViewState>()

    init {
        viewStateLiveData.value = MainViewState(NotesRepository.getNotes())
    }
    fun viewState():LiveData<MainViewState> = viewStateLiveData
}