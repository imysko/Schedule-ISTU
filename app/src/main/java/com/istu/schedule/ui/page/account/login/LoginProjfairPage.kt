package com.istu.schedule.ui.page.account.login

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.istu.schedule.ui.util.NavDestinations

@Composable
fun LoginProjfairPage(
    navController: NavHostController,
    viewModel: LoginProjfairViewModel = hiltViewModel()
) {
    val loginUrl = "https://int.istu.edu/oauth/authorize/?client_id=local.6149ff4c7fcf40.88217011"
    val context = LocalContext.current

    AndroidView(
        factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )

                webViewClient = object : WebViewClient() {
                    var currentUrl = ""

                    @Deprecated("Deprecated in Java")
                    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                        view.loadUrl(url)
                        return true
                    }

                    override fun onPageFinished(view: WebView, url: String) {
                        if (currentUrl != url) {
                            val imm = context.getSystemService(
                                Context.INPUT_METHOD_SERVICE
                            ) as InputMethodManager

                            if (url.contains("https://int.istu.edu/oauth/authorize")) {
                                imm.showSoftInput(view, 0)
                                view.visibility = View.VISIBLE
                            }
                            if (url.contains("https://projfair.istu.edu")) {
                                view.visibility = View.INVISIBLE
                                val cookies = CookieManager.getInstance().getCookie(url)
                                viewModel.login(cookies)
                                navController.navigate(NavDestinations.MAIN) {
                                    popUpTo(0)
                                }
                            }
                        }

                        currentUrl = url
                    }
                }

                loadUrl(loginUrl)
            }
        },
        update = {
            it.loadUrl(loginUrl)
        }
    )
}
