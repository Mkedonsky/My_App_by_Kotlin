package ru.mkedonsky.myappbykotlin.data

import ru.mkedonsky.myappbykotlin.data.entyty.Note
import ru.mkedonsky.myappbykotlin.data.provider.FirestoreProvider
import ru.mkedonsky.myappbykotlin.data.provider.RemoteDataProvider


object NotesRepository {

    private val remoteProvider: RemoteDataProvider = FirestoreProvider()
    fun getNotes() = remoteProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteProvider.saveNote(note)
    fun getNoteById(id: String) = remoteProvider.getNoteById(id)
    fun getCurrentUser() = remoteProvider.getCurrentUser()

}