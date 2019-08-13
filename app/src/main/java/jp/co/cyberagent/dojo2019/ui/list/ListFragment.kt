package jp.co.cyberagent.dojo2019.ui.list

import android.os.Bundle
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
import javax.inject.Inject


class ListFragment : DaggerFragment() {

    private lateinit var viewModel: ListViewModel
    private val listAdapter = ListAdapter(arrayListOf())

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)
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


        viewModel.userList.observe(this, Observer<List<User>>{list ->
            list?.let {
                listAdapter.updateUserList(it)
            }
        })

        recyclerView.apply{
            layoutManager = GridLayoutManager(context, 2)
            adapter = listAdapter
        }
    }
}