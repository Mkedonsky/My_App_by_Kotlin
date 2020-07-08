package ru.mkedonsky.myappbykotlin.data

import ru.mkedonsky.myappbykotlin.data.entyty.Note

object NotesRepository {
    private val notes : List<Note> = listOf(
        Note(
            "Моя первая заметка",
        "Текст первой заметки",
            0xfff06292.toInt()
        ),
        Note(
            "Моя вторая заметка",
            "Текст второй заметки",
            0xff9575cd.toInt()
        ),
        Note(
            "Моя третья заметка",
            "Текст третьей заметки",
            0xff64b5f6.toInt()
        ),
        Note(
            "Моя четвертая заметка",
            "Текст четвертой заметки",
            0xff4db6ac.toInt()
        ),
        Note(
            "Моя пятая заметка",
            "Текст пятой заметки",
            0xffb2ff59.toInt()
        ),
        Note(
            "Моя шестая заметка",
            "Текст шестой заметки",
            0xffffeb3b.toInt()
        )


    )

    fun getNotes(): List<Note>{
        return notes
    }

}