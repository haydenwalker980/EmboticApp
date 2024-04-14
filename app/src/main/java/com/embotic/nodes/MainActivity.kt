package com.embotic.nodes

import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


private const val TAG = "MainActivity"

class MainActivity : BaseActivity() {
    lateinit var webView: WebView

    private val dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val permissionState =
            ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
        // If the permission is not granted, request it.
        if (permissionState == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                1
            )
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        webView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.allowContentAccess = true
        webView.settings.allowFileAccess = true
        webView.settings.setSupportZoom(true)
        webView.isClickable = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                url: String): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView.loadUrl("https://dash.embotic.xyz/")

    }
}
