package ru.mkedonsky.myappbykotlin.ui.main

import ru.mkedonsky.myappbykotlin.data.entyty.Note
import ru.mkedonsky.myappbykotlin.ui.base.BaseViewState

class MainViewState(val notes: List<Note>? = null, error: Throwable? = null) :
    BaseViewState<List<Note>?>(notes, error)