package ru.mkedonsky.myappbykotlin.ui

import android.app.Application
import org.koin.core.context.startKoin
import ru.mkedonsky.myappbykotlin.di.appModule
import ru.mkedonsky.myappbykotlin.di.mainModule
import ru.mkedonsky.myappbykotlin.di.noteModule
import ru.mkedonsky.myappbykotlin.di.splashModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin { modules(appModule, splashModule, mainModule, noteModule) }
    }
}