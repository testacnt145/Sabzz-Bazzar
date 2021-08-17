package com.sabzzbazzar

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.*
import com.sabzzbazzar.databinding.ActivityMainBinding
import okhttp3.Cookie


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main,{ActivityMainBinding.inflate(it)}) {

    val url = "https://sabzzbazzar.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webViewSettings()
        //setCookies()
        webViewClient()
        loadUrl()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSettings() {
        val settings = vB?.webview?.settings
        settings?.javaScriptEnabled = true
        settings?.textZoom = 90
        vB?.webview?.scrollBarStyle = View.SCROLLBARS_OUTSIDE_OVERLAY
        vB?.webview?.settings?.builtInZoomControls = false
        vB?.webview?.settings?.displayZoomControls = false
        vB?.webview?.settings?.useWideViewPort = true
        vB?.webview?.settings?.loadWithOverviewMode = true
        vB?.webview?.settings?.setRenderPriority(WebSettings.RenderPriority.HIGH)
        //removed cache, as per backend
        vB?.webview?.settings?.setAppCacheEnabled(false)
        vB?.webview?.settings?.cacheMode = WebSettings.LOAD_NO_CACHE
        vB?.webview?.settings?.domStorageEnabled = true
        vB?.webview?.setLayerType(View.LAYER_TYPE_HARDWARE, null)
        vB?.webview?.isClickable = true
        WebView.setWebContentsDebuggingEnabled(true)
        vB?.webview?.webChromeClient = (object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)

            }
        })
    }

    private fun checkIfPageCanGoBack(): Boolean {
        return vB?.webview?.canGoBack()!!
    }

    override fun onBackPressed() {
        if (checkIfPageCanGoBack())
            vB?.webview?.goBack()
        else
            finish()
    }

//    private fun setCookies() {
//        val cookieManager = CookieManager.getInstance()
//        cookieManager.setAcceptCookie(true)
//        cookieManager.removeAllCookie()
//        //String cookie = NetworkConstant.COOKIE_CITY_ID + "=" + Convert.intToString(Pref.getSelectedCityId());
//        //cookieManager.setCookie(presenter.url, cookie + " ; path=/ ;domain=.savyour.com.pk");
//        val b1 = Cookie.Builder()
//            .domain("gstatic.com")
//            .path("/")
//            .httpOnly()
//            .secure()
//            .name("1P_JAR")
//            .value("2021-08-01-09")
//        val c1 = b1.build()
//        cookieManager.setCookie(url, c1.toString())
//    }

    private fun webViewClient() {
        vB?.webview?.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                Log.e("shouldOverrideUrlLoad", url.toString())
                view.loadUrl(url.toString())
                return true
            }
            override fun onPageFinished(view: WebView, url: String) {
                Log.e("onPageFinished", url)
            }
            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
                Log.e("onReceivedError", failingUrl)
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadUrl() {
        Log.e("loadUrl", url)
        vB?.webview?.loadUrl(url)
    }
}