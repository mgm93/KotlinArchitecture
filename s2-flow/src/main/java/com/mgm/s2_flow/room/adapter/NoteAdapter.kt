package com.mgm.s2_flow.room.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mgm.s2_flow.databinding.ItemNotesBinding
import com.mgm.s2_flow.room.db.NoteModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Majid-Golmoradi on 12/19/2022.
 * Email: golmoradi.majid@gmail.com
 */
@Singleton
class NoteAdapter @Inject constructor() : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private lateinit var binding: ItemNotesBinding
    private var noteList = emptyList<NoteModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        binding = ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        holder.setData(noteList[position])
    }

    override fun getItemCount() = noteList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun setData(item: NoteModel) {
            binding.apply {
                deleteImg.setOnClickListener {
                    titleTxt.text = "${item.id} : ${item.name}"
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((NoteModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (NoteModel) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(data: List<NoteModel>?) {
        val noteDiffUtil = NoteDiffUtils(noteList, data!!)
        val diffUtils = DiffUtil.calculateDiff(noteDiffUtil)
        noteList = data
        diffUtils.dispatchUpdatesTo(this)
    }

    class NoteDiffUtils(
        private val oldNoteList: List<NoteModel>,
        private val newNoteList: List<NoteModel>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldNoteList.size
        }

        override fun getNewListSize(): Int {
            return newNoteList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldNoteList[oldItemPosition] == newNoteList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldNoteList[oldItemPosition] == newNoteList[newItemPosition]
        }

    }
}