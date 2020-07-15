package ru.mkedonsky.myappbykotlin.ui.main.note

import androidx.lifecycle.ViewModel
import ru.mkedonsky.myappbykotlin.data.NotesRepository
import ru.mkedonsky.myappbykotlin.data.entyty.Note

class NoteViewModel() : ViewModel() {
    private var pendingNote: Note? = null

    fun save(note: Note){
        pendingNote = note
    }

    override fun onCleared() {
        pendingNote?.let {
            NotesRepository.saveNote(it)
        }
    }
}