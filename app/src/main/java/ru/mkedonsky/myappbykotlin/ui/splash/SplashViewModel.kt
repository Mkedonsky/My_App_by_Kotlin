package ru.mkedonsky.myappbykotlin.ui.splash

import ru.mkedonsky.myappbykotlin.data.NotesRepository
import ru.mkedonsky.myappbykotlin.data.error.NoAuthException
import ru.mkedonsky.myappbykotlin.ui.base.BaseViewModel

class SplashViewModel() : BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser() {
        NotesRepository.getCurrentUser().observeForever {
            viewStateLiveData.value = it?.let {
                SplashViewState(authenticated = true)
            } ?: let {
                SplashViewState(error = NoAuthException())
            }
        }
    }

}