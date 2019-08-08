package jp.co.cyberagent.dojo2019.ui.user

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import dagger.android.AndroidInjection
import dagger.android.support.DaggerFragment

import jp.co.cyberagent.dojo2019.R
import jp.co.cyberagent.dojo2019.di.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_user.*
import javax.inject.Inject
import kotlin.concurrent.thread


class UserFragment : DaggerFragment() {

    private lateinit var viewModel: UserViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.name.observe(this, Observer {
            edtName.setText(it)
        })
        viewModel.githubAccount.observe(this, Observer {
            edtGitAccount.setText(it)
        })
        viewModel.twitterAccount.observe(this, Observer {
            edtTwiAccount.setText(it)
        })
        btnSave.setOnClickListener {

            viewModel.saveMyInfo(edtName.text.toString(), edtGitAccount.text.toString(), edtTwiAccount.text.toString())
            val action = UserFragmentDirections.actionQRcodeFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}