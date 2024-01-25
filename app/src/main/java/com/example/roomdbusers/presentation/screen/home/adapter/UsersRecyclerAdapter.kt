package com.example.roomdbusers.presentation.screen.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdbusers.databinding.RecyclerUsersBinding
import com.example.roomdbusers.presentation.model.Users

class UsersRecyclerAdapter: ListAdapter<Users, UsersRecyclerAdapter.UsersViewHolder>(
    UserListDiffCallback()
) {
    private var onItemClickListener: ((Users) -> Unit)? = null


    inner class UsersViewHolder(private val binding: RecyclerUsersBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind() = with(binding) {
            val user = currentList[adapterPosition]

            tvFirstName.text = user.firstName
            tvLastName.text = user.lastName
            tvAge.text = user.age
            tvEmail.text = user.email

        }

        fun userUpdate() {
            val user = currentList[adapterPosition]

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it.invoke(user)
                }
            }

        }

    }

    fun setOnItemClickListener(listener: (Users) -> Unit) {
        onItemClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(
            RecyclerUsersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind()
        holder.userUpdate()
    }

    class UserListDiffCallback : DiffUtil.ItemCallback<Users>() {
        override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {
            return oldItem == newItem
        }
    }

}