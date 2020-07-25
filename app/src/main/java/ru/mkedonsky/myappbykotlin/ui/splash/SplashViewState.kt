package ru.mkedonsky.myappbykotlin.ui.splash

import ru.mkedonsky.myappbykotlin.ui.base.BaseViewState

class SplashViewState(authenticated: Boolean? = null, error: Throwable? = null) :
    BaseViewState<Boolean?>(authenticated, error)