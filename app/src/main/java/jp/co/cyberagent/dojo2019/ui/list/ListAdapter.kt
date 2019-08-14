package jp.co.cyberagent.dojo2019.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2019.R
import jp.co.cyberagent.dojo2019.data.db.entity.User
import kotlinx.android.synthetic.main.fragment_qrcode.view.*
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.list_item.view.txtGitAccount
import kotlinx.android.synthetic.main.list_item.view.txtTwiAccount

class ListAdapter(
    private val userList: ArrayList<User>
) : RecyclerView.Adapter<ListAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]

        holder.view.txtName.text = user.name
        holder.view.txtGitAccount.apply {
            txtGitAccount
            text = user.githubAccount
            setOnClickListener {
                //idとhostをWebViewFragmentに渡す
                val bundle = Bundle().apply {
                    putString("id", txtGitAccount.text.toString())
                    putString("host", txtGitAccount.getTag().toString())
                }
                Navigation.findNavController(it).navigate(R.id.actionWebViewFragment, bundle)
            }
        }
        holder.view.txtTwiAccount.apply {
            text = user.twitterAccount
            setOnClickListener {txtTwiAccount
                val bundle = Bundle().apply {
                    putString("id", txtTwiAccount.text.toString())
                    putString("host", txtTwiAccount.getTag().toString())
                }
                Navigation.findNavController(it).navigate(R.id.actionWebViewFragment, bundle)
            }
        }

    }

    fun updateUserList(newUserList: List<User>) {
        userList.clear()
        userList.addAll(newUserList)
        notifyDataSetChanged()
    }

    class UserViewHolder(var view: View) : RecyclerView.ViewHolder(view)

}