package com.mgm.s1_hilt.room

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mgm.s1_hilt.databinding.ItemNotesBinding
import com.mgm.s1_hilt.room.db.NoteModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Majid-Golmoradi on 8/21/2022.
 * Email: golmoradi.majid@gmail.com
 */

@Singleton
class NoteAdapter @Inject constructor() : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    //binding
    private lateinit var binding: ItemNotesBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        binding = ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size


    //added to fix duplicate data
    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun setData(item:NoteModel){
            binding.apply {
                titleTxt.text = "${item.id} : ${item.title}"
                root.setOnClickListener {
                    onClickListener?.let {
                        it(item) }
                }
            }
        }
    }

    //onClick
    private var onClickListener : ((NoteModel) -> Unit)? = null

    fun setOnItemClickListener(listener: ((NoteModel)-> Unit)){
        onClickListener = listener
    }

    private val differCallBack = object : DiffUtil.ItemCallback<NoteModel>(){
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this,differCallBack)
}