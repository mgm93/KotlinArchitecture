package com.mgm.s1_room

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mgm.s1_room.databinding.ItemUserBinding
import com.mgm.s1_room.db.UserEntity

/**
 * Created by Majid-Golmoradi on 7/13/2022.
 * Email: golmoradi.majid@gmail.com
 */
class UsersAdapter : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    private lateinit var binding: ItemUserBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemUserBinding.inflate(inflater,parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: UsersAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(item:UserEntity){
            binding.txtItemUserInfo.text = "${item.userId} - ${item.userName} -> ${item.userAge}"
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<UserEntity>(){
        override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem.userId == newItem.userId
        }

        override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)
}