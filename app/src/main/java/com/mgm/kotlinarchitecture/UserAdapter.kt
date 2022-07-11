package com.mgm.kotlinarchitecture

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mgm.kotlinarchitecture.databinding.ItemUserBinding

/**
 * Created by Majid-Golmoradi on 7/11/2022.
 * Email: golmoradi.majid@gmail.com
 */
class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var binding: ItemUserBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        holder.setDate(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size

    inner class ViewHolder: RecyclerView.ViewHolder(binding.root){

        @SuppressLint("SetTextI18n")
        fun setDate(item: UserModel){
            binding.txtItemUserInfo.text = "${item.name} ${item.age}"
        }
    }

    private val callBack = object : DiffUtil.ItemCallback<UserModel>(){
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callBack)
}