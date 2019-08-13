package jp.co.cyberagent.dojo2019.ui.web

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient

import jp.co.cyberagent.dojo2019.R
import kotlinx.android.synthetic.main.web_view_fragment.*

class WebViewFragment : Fragment() {

    private var id: String? = null
    lateinit var host: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.web_view_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            id = it.getString("id")
            host = it.getString("host")!!
        }

        val uri = Uri.Builder()
            .scheme("https")
            .authority(host)
            .path(id)
        val url = uri.toString()
        Log.d("TAG", url)
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(url)
            settings.javaScriptEnabled = true
        }
    }

}
