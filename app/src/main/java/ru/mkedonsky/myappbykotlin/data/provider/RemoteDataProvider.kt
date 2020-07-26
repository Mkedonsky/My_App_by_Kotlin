package ru.mkedonsky.myappbykotlin.data.provider

import androidx.lifecycle.LiveData
import ru.mkedonsky.myappbykotlin.data.entyty.Note
import ru.mkedonsky.myappbykotlin.data.entyty.User
import ru.mkedonsky.myappbykotlin.data.model.NoteResult

interface RemoteDataProvider {
    fun subscribeToAllNotes(): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
    fun saveNote(note: Note): LiveData<NoteResult>
    fun getCurrentUser(): LiveData<User>
    fun deleteNote(noteId: String): LiveData<NoteResult>
}