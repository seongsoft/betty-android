package io.github.cbinarycastle.betty.ui

import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.github.cbinarycastle.betty.BuildConfig
import io.github.cbinarycastle.betty.R
import timber.log.Timber

class ExitDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = layoutInflater.inflate(R.layout.dialog_exit, null)
        val templateView = view.findViewById<TemplateView>(R.id.ad_template)

        loadNativeAd { templateView.setNativeAd(it) }

        return MaterialAlertDialogBuilder(requireContext())
            .setView(view)
            .setPositiveButton(R.string.exit) { _, _ ->
                requireActivity().finish()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.cancel()
            }
            .create()
    }

    private fun loadNativeAd(onNativeAdLoaded: (NativeAd) -> Unit) {
        AdLoader.Builder(requireContext(), BuildConfig.ADMOB_NATIVE_AD_UNIT_ID)
            .forNativeAd(onNativeAdLoaded)
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    Timber.e("AdLoader error: $p0")
                }
            })
            .build()
            .loadAd(AdRequest.Builder().build())
    }
}