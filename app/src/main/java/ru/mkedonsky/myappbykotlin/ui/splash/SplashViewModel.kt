package ru.mkedonsky.myappbykotlin.ui.splash

import androidx.lifecycle.Observer
import ru.mkedonsky.myappbykotlin.common.observeOnce
import ru.mkedonsky.myappbykotlin.data.NotesRepository
import ru.mkedonsky.myappbykotlin.data.error.NoAuthException
import ru.mkedonsky.myappbykotlin.ui.base.BaseViewModel

class SplashViewModel(val repository: NotesRepository) :
    BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser() {
        repository.getCurrentUser().observeOnce(Observer { user ->
            viewStateLiveData.value = user?.let {
                SplashViewState(authenticated = true)
            } ?: let {
                SplashViewState(error = NoAuthException())
            }
        })
    }
}