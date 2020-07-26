package ru.mkedonsky.myappbykotlin.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.mkedonsky.myappbykotlin.data.NotesRepository
import ru.mkedonsky.myappbykotlin.data.provider.FirestoreProvider
import ru.mkedonsky.myappbykotlin.data.provider.RemoteDataProvider
import ru.mkedonsky.myappbykotlin.ui.main.MainViewModel
import ru.mkedonsky.myappbykotlin.ui.note.NoteViewModel
import ru.mkedonsky.myappbykotlin.ui.splash.SplashViewModel

val appModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single<RemoteDataProvider> { FirestoreProvider(get(), get()) }
    single { NotesRepository(get()) }
}

val splashModule = module {
    viewModel { SplashViewModel(get()) }
}

val mainModule = module {
    viewModel { MainViewModel(get()) }
}

val noteModule = module {
    viewModel { NoteViewModel(get()) }
}