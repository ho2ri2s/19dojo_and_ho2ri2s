package jp.co.cyberagent.dojo2019.ui.list

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import jp.co.cyberagent.dojo2019.R
import jp.co.cyberagent.dojo2019.data.User
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


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
        val userList = arrayListOf<User>()
        val user1: User = User("hori yuta", "ho2ri2s", "hohohoris")
        val user2: User = User("yamauti ran", "run", "yamauti")
        val user3: User = User("wao", "waowao", "waowaowao")
        val user4: User = User("aiueo", "hoho", "hohoh")
        userList.add(user1)
        userList.add(user2)
        userList.add(user3)
        userList.add(user4)

        recyclerView.apply{
            layoutManager = GridLayoutManager(context, 2)
            adapter = ListAdapter(userList)
        }
    }
}