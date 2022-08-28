package com.mgm.s1_livedata_viewmodel.room.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mgm.s1_livedata_viewmodel.databinding.ItemNotesBinding
import com.mgm.s1_livedata_viewmodel.room.db.NoteEntity

/**
 * Created by Majid-Golmoradi on 8/27/2022.
 * Email: golmoradi.majid@gmail.com
 */
class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    //Binding
    private lateinit var binding: ItemNotesBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        binding = ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        //getItem from PagingDataAdapter
        holder.bindData(differ.currentList[position])
        //Not duplicate items
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindData(item: NoteEntity) {
            binding.apply {
                titleTxt.text = item.title
                descTxt.text = item.desc
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<NoteEntity>() {
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)
}