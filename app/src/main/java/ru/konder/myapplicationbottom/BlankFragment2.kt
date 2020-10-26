package ru.konder.myapplicationbottom

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_blank.*


class BlankFragment2 : Fragment() {
    lateinit var web : WebView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        web = webView
        web.webViewClient = myWebClient()
        web.loadUrl("https://vk.com/im")
        web.settings?.javaScriptEnabled = true
    }
    inner class myWebClient : WebViewClient(){
        override fun shouldOverrideUrlLoading(view: WebView, url:String): Boolean {
            view.loadUrl(url)
            return true
        }
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
        }
    }
}