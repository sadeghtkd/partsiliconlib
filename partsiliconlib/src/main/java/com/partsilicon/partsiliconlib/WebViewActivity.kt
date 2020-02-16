package com.partsilicon.partsiliconlib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web_view.*
import android.webkit.JavascriptInterface
import android.app.Activity
import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.view.View


class WebViewActivity : AppCompatActivity() {

    internal inner class JavaScriptInterface(private val activity: Activity) {

        @JavascriptInterface
        fun closeAct() {

            activity.finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        setSupportActionBar(toolbar)

        title = intent.getStringExtra("title")

        val progressBar = findViewById(R.id.progressBar) as ProgressBar
        val wv = findViewById(R.id.webview) as WebView

        val choice = intent.getBooleanExtra("fake", false) //implented for Google Play Policy
        wv.loadUrl(intent.getStringExtra("url"))
        wv.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                progressBar.visibility = View.GONE
            }

            override fun onReceivedSslError(
                view: WebView,
                handler: SslErrorHandler,
                error: SslError
            ) {
                if (choice)
                    handler.cancel()
                else
                    handler.proceed() // Ignore SSL certificate errors
            }
        }
        wv.settings.javaScriptEnabled = true
        wv.addJavascriptInterface(JavaScriptInterface(this), "JSInterface")
    }

}
