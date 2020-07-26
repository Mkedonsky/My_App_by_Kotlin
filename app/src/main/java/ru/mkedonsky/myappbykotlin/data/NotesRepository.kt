package ru.mkedonsky.myappbykotlin.data

import ru.mkedonsky.myappbykotlin.data.entyty.Note
import ru.mkedonsky.myappbykotlin.data.provider.RemoteDataProvider


class NotesRepository(val provider: RemoteDataProvider) {

    fun getNotes() = provider.subscribeToAllNotes()
    fun saveNote(note: Note) = provider.saveNote(note)
    fun getNoteById(id: String) = provider.getNoteById(id)
    fun getCurrentUser() = provider.getCurrentUser()
    fun deleteNote(noteId: String) = provider.deleteNote(noteId)

}