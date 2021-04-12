package com.techjd.userlisttest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.techjd.userlisttest.data.Data
import com.techjd.userlisttest.databinding.ItemUsersBinding
import javax.inject.Inject

class UserAdapter @Inject constructor(private val glide: RequestManager) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var usersList = emptyList<Data>()

    inner class UserViewHolder(val binding: ItemUsersBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUsersBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        with(holder) {
            with(usersList[position]) {
                glide.load(this.avatar).into(binding.avatar)
                binding.name.text = "${this.first_name} ${this.last_name}"
                binding.email.text = this.email
            }
        }
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    fun setdata(users: List<Data>) {
        this.usersList = users
        notifyDataSetChanged()
    }
}