package jp.co.cyberagent.dojo2019.ui.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import dagger.android.support.DaggerFragment

import jp.co.cyberagent.dojo2019.R
import jp.co.cyberagent.dojo2019.data.db.entity.User
import jp.co.cyberagent.dojo2019.databinding.DetailFragmentBinding
import jp.co.cyberagent.dojo2019.di.ViewModelFactory
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.detail_fragment.txtGitAccount
import kotlinx.android.synthetic.main.list_item.view.*
import javax.inject.Inject

class DetailFragment : DaggerFragment() {

    private lateinit var viewModel: DetailViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var user: User
    private lateinit var binding: DetailFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //navigation経由でuser情報を入手
        arguments?.let {
            user = DetailFragmentArgs.fromBundle(it).user
        }
        //画面回転でデータ破棄を防ぐためviewModelに保存
        Log.d("TAG", "fragment : ${binding.viewModel} user : ${user}")
        binding.viewModel?.postUserToViewModel(user)

        //clickするとwebViewでgithubサイトへ飛ぶ
        txtGitAccount.apply {
//            text = user.githubAccount
            setOnClickListener {
                //idとhostをWebViewFragmentに渡す
                val bundle = Bundle().apply {
                    putString("id", txtGitAccount.text.toString())
                    putString("host", txtGitAccount.getTag().toString())
                }
                Navigation.findNavController(it).navigate(R.id.actionWebViewFragment, bundle)
            }
        }
        //clickするとwebViewでtwitterサイトへ飛ぶ
        txtTwiAccount.apply {
//            text = user.twitterAccount
            setOnClickListener {
                txtTwiAccount
                val bundle = Bundle().apply {
                    putString("id", txtTwiAccount.text.toString())
                    putString("host", txtTwiAccount.getTag().toString())
                }
                Navigation.findNavController(it).navigate(R.id.actionWebViewFragment, bundle)
            }
        }
        if(!user.githubAccount.isNullOrEmpty()) {
            Glide.with(view)
                .load(user.githubImage)
                .into(imgGitHub)
        }

    }
}
