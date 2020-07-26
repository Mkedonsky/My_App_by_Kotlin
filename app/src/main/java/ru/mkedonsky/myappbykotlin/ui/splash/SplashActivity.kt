package ru.mkedonsky.myappbykotlin.ui.splash

import org.koin.android.viewmodel.ext.android.viewModel
import ru.mkedonsky.myappbykotlin.ui.base.BaseActivity
import ru.mkedonsky.myappbykotlin.ui.main.MainActivity

class SplashActivity : BaseActivity<Boolean?, SplashViewState>() {
    override val viewModel: SplashViewModel by viewModel()

    override val layoutRes = null

    override fun onResume() {
        super.onResume()
        viewModel.requestUser()
    }

    override fun renderData(data: Boolean?) {
        data?.takeIf { it }?.let {
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        MainActivity.start(this)
        finish()
    }
}