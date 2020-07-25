package ru.mkedonsky.myappbykotlin.ui.note

import ru.mkedonsky.myappbykotlin.data.NotesRepository
import ru.mkedonsky.myappbykotlin.data.entyty.Note
import ru.mkedonsky.myappbykotlin.data.model.NoteResult
import ru.mkedonsky.myappbykotlin.ui.base.BaseViewModel


class NoteViewModel() : BaseViewModel<Note?, NoteViewState>() {

    init {
        viewStateLiveData.value = NoteViewState()
    }

    private var pendingNote: Note? = null

    fun save(note: Note) {
        pendingNote = note
    }

    fun loadNote(noteId: String) {
        NotesRepository.getNoteById(noteId).observeForever { result ->
            result ?: return@observeForever
            when (result) {
                is NoteResult.Success<*> -> viewStateLiveData.value =
                    NoteViewState(note = result.data as? Note)
                is NoteResult.Error -> viewStateLiveData.value = NoteViewState(error = result.error)
            }
        }
    }

    override fun onCleared() {
        pendingNote?.let {
            NotesRepository.saveNote(it)
        }
    }
}