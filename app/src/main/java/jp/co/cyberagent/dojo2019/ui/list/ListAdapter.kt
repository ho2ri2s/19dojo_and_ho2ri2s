package jp.co.cyberagent.dojo2019.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2019.R
import jp.co.cyberagent.dojo2019.data.entity.User
import kotlinx.android.synthetic.main.list_item.view.*

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
        holder.view.txtGitAccount.text = user.githubAccount
        holder.view.txtTwiAccount.text = user.twitterAccount
    }

    class UserViewHolder(var view: View): RecyclerView.ViewHolder(view)

}