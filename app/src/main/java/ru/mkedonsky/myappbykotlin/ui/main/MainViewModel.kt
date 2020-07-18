package ru.mkedonsky.myappbykotlin.ui.main

import androidx.lifecycle.Observer
import ru.mkedonsky.myappbykotlin.data.NotesRepository
import ru.mkedonsky.myappbykotlin.data.entyty.Note
import ru.mkedonsky.myappbykotlin.data.model.NoteResult
import ru.mkedonsky.myappbykotlin.ui.base.BaseViewModel

class MainViewModel : BaseViewModel<List<Note>?, MainViewState>() {

    private val notesObserver = Observer { result: NoteResult ->
        when (result) {
            is NoteResult.Success<*> -> viewStateLiveData.value = MainViewState(
                notes = result
                    .data as? List<Note>
            )
            is NoteResult.Error -> viewStateLiveData.value = MainViewState(error = result.error)
        }
    }

    private val repositoryNotes = NotesRepository.getNotes()

    init {
        viewStateLiveData.value = MainViewState()
        repositoryNotes.observeForever(notesObserver)
    }


    override fun onCleared() {
        super.onCleared()
        repositoryNotes.removeObserver(notesObserver)
    }
}