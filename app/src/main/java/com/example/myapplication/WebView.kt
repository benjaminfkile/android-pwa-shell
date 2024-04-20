import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.activity.compose.BackHandler

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(url: String) {
    // Context from the Compose environment
    val context = LocalContext.current
    // Remembering the WebView to maintain its state across recompositions
    val webView = remember {
        WebView(context).apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true // Enable JavaScript
        }
    }

    // Load the URL
    DisposableEffect(Unit) {
        webView.loadUrl(url)
        onDispose { }
    }

    // Embedding the WebView into the Compose UI
    AndroidView(
        factory = { webView },
        modifier = Modifier.fillMaxSize()
    )

    // Handling back navigation within the WebView
    BackHandler(enabled = webView.canGoBack()) {
        webView.goBack()
    }
}
