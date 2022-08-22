package com.mgm.s1_hilt.retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mgm.s1_hilt.databinding.ItemMoviesBinding
import com.mgm.s1_hilt.retrofit.models.ResponseMovies
import javax.inject.Inject


/**
 * Created by Majid-Golmoradi on 7/17/2022.
 * Email: golmoradi.majid@gmail.com
 */
class MoviesAdapter @Inject constructor() :RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private lateinit var binding: ItemMoviesBinding
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.ViewHolder {
        context = parent.context
        binding = ItemMoviesBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: MoviesAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size

    //added to fix duplicate data
    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){
        fun bind(item: ResponseMovies.Data){
            binding.apply {
                movieName.text = item.title
                movieImg.load(item.poster){
                    crossfade(true)
                    crossfade(800)
                }
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<ResponseMovies.Data>(){
        override fun areItemsTheSame(
            oldItem: ResponseMovies.Data,
            newItem: ResponseMovies.Data
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseMovies.Data,
            newItem: ResponseMovies.Data
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)
}