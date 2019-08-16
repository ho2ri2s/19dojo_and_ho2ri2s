package jp.co.cyberagent.dojo2019.ui.splash

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.Navigation

import jp.co.cyberagent.dojo2019.R
import kotlinx.android.synthetic.main.fragment_splash.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SplashFragment : Fragment(), CoroutineScope {

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val animation = AnimationUtils.loadAnimation(context, R.anim.fadeout_alpha)
        imgSplash.startAnimation(animation)

        launch {
            delay(3000)
            val action = SplashFragmentDirections.actionUserFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}
