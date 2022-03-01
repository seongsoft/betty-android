package io.github.cbinarycastle.betty.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import dagger.hilt.android.AndroidEntryPoint
import io.github.cbinarycastle.betty.BuildConfig
import io.github.cbinarycastle.betty.R
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<AppViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.exitSignal.collect {
                openExitDialog()
            }
        }

        setContent {
            BettyApp(viewModel)
        }
    }

    private fun openExitDialog() {
        ExitDialogFragment().show(supportFragmentManager, null)
    }
}