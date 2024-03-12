package com.embotic.nodes

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    lateinit var webView: WebView
    var appRater: im.delight.apprater.AppRater? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        webView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.allowContentAccess = true
        webView.settings.allowFileAccess = true
        webView.settings.setSupportZoom(true)
        webView.isClickable = true
        appRater = im.delight.apprater.AppRater(this)
        appRater?.setDaysBeforePrompt(0)
        appRater?.setLaunchesBeforePrompt(0)
        appRater?.show()
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                url: String): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView.loadUrl("https://panel.embotic.xyz/")

    }
}
