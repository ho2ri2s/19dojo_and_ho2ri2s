package jp.co.cyberagent.dojo2019.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.support.DaggerFragment

import jp.co.cyberagent.dojo2019.R
import jp.co.cyberagent.dojo2019.data.entity.User
import jp.co.cyberagent.dojo2019.di.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class ListFragment : DaggerFragment(), CoroutineScope {

    private lateinit var viewModel: ListViewModel
    private val listAdapter = ListAdapter(arrayListOf())
    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)

        //CoroutineContextのJobをインスタンス化
        job = Job()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //awaitなどのCoroutineビルダーはCoroutineもしくは中断関数(suspend fun)から呼ぶ
        launch {
            val userList = viewModel.userList.await()

            userList.observe(this@ListFragment, Observer<List<User>> { list ->
                list?.let {
                    listAdapter.updateUserList(it)
                }
            })
        }


        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = listAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //親Jobをキャンセルすることで全てのcoroutineブロックをキャンセル
        job.cancel()
    }

}