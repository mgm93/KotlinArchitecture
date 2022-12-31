package com.mgm.s2_flow.retrofit.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mgm.s2_flow.databinding.ItemMoviesBinding
import com.mgm.s2_flow.retrofit.model.ResponseMovies.*
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 9/8/2022.
 * Email: golmoradi.majid@gmail.com
 */
class ListMoviesAdapter @Inject constructor() :
    RecyclerView.Adapter<ListMoviesAdapter.ViewHolder>() {
    //Binding
    private lateinit var binding: ItemMoviesBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListMoviesAdapter.ViewHolder {
        binding =
            ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ListMoviesAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Data) {
            binding.apply {
                movieImg.load(item.poster) {
                    crossfade(true)
                    crossfade(800)
                }
                movieName.text = item.title
            }
        }
    }
    //onClick
    private var onItemClickListener : ((Data) -> Unit)? = null

    fun setOmItemClickListener(listener: (Data) -> Unit){
        onItemClickListener = listener
    }


    class MoviesDiffUtil(private val oldItem: List<Data>, private val newItem: List<Data>) :
        DiffUtil.Callback() {
        override fun getOldListSize() = oldItem.size

        override fun getNewListSize() = newItem.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition].id === newItem[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

    }

    private val differCallBack = object: DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)
}