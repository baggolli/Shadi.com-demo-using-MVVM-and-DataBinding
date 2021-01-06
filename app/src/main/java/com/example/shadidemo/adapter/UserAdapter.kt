package com.example.shadidemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shadidemo.R
import com.example.shadidemo.database.entity.UserEntity
import com.example.shadidemo.databinding.UserListItemBinding

class UserAdapter (private val userList: ArrayList<UserEntity>,private val listener: RecyclerViewClickListener) : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.user_list_item, parent, false))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.userListItemBinding.user = userList[position]
        holder.userListItemBinding.declineIcon.setOnClickListener {
            listener.onRecyclerViewItemClick(holder.userListItemBinding.declineIcon, userList[position])
        }
        holder.userListItemBinding.acceptIcon.setOnClickListener {
            listener.onRecyclerViewItemClick(holder.userListItemBinding.acceptIcon, userList[position])
        }
    }

    override fun getItemCount() = userList.size

    inner class UserViewHolder(val userListItemBinding: UserListItemBinding) : RecyclerView.ViewHolder(userListItemBinding.root)

    fun updateUserList(list: List<UserEntity>) {
        userList.clear()
        userList.addAll(list)
        notifyDataSetChanged()
    }

    interface RecyclerViewClickListener {
        fun onRecyclerViewItemClick(view: View, userEntity: UserEntity)
    }
}