package com.istu.schedule.ui.page.newsfeed.login

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.istu.schedule.ui.util.NavDestinations

@Composable
fun LoginCampusPage(
    navController: NavHostController,
    viewModel: LoginCampusViewModel = hiltViewModel()
) {
    val loginUrl =
        "https://int.istu.edu/oauth/authorize/?login=yes&client_id=local.660a7aeddc2e07.46112865"
    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        if (uiState is LoginCampusUiState.Success) {
            navController.navigate(NavDestinations.MAIN) {
                popUpTo(0)
            }
        }
    }

    if (uiState is LoginCampusUiState.Unauthorized) {
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
                                if (url.contains("?code=")) {
                                    view.visibility = View.INVISIBLE
                                    viewModel.login(url)
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
}
