package jp.co.cyberagent.dojo2019.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jp.co.cyberagent.dojo2019.R
import jp.co.cyberagent.dojo2019.data.db.entity.User
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

        holder.view.linearLayout.apply {
            setOnClickListener{
                val action = ListFragmentDirections.actionDetailFragment(user)
                Navigation.findNavController(it).navigate(action)
            }
        }

        holder.view.txtGitAccount.apply {
            text = user.githubAccount
        }

        if(!user.githubAccount.isNullOrEmpty()){
            Glide.with(holder.view)
                .load(user.githubImage)
                .into(holder.view.imgGitHub)
        }
    }

    fun updateUserList(newUserList: List<User>) {
        userList.clear()
        userList.addAll(newUserList)
        notifyDataSetChanged()
    }

    class UserViewHolder(var view: View) : RecyclerView.ViewHolder(view)

}