package com.mgm.s2_flow.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mgm.s2_flow.databinding.ActivityMoviesGenresBinding
import com.mgm.s2_flow.retrofit.adapter.GenresAdapter
import com.mgm.s2_flow.retrofit.adapter.ListMoviesAdapter
import com.mgm.s2_flow.retrofit.viewmodel.MoviesViewModelWithGenres
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@AndroidEntryPoint
class MoviesGenresActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding : ActivityMoviesGenresBinding

    @Inject
    lateinit var moviesAdapter: ListMoviesAdapter

    @Inject
    lateinit var genresAdapter: GenresAdapter

    private val viewModel: MoviesViewModelWithGenres by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesGenresBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Init views
        binding.apply {
            lifecycleScope.launchWhenCreated {
                viewModel.loadMoviesAndGenres()
                    .onStart { loading.visibility = View.VISIBLE }
                    .onCompletion { loading.visibility = View.GONE }
                    .collect{
                        val (movies, genres) = it.unzip()
                        //movies
                        moviesAdapter.differ.submitList(movies)
                        listMovies.apply {
                            layoutManager = LinearLayoutManager(this@MoviesGenresActivity)
                            adapter = moviesAdapter
                        }
                        //genres
                        genresAdapter.differ.submitList(genres)
                        listGenres.apply {
                            layoutManager = LinearLayoutManager(this@MoviesGenresActivity,LinearLayoutManager.HORIZONTAL, false)
                            adapter = genresAdapter
                        }
                    }

            }

        }
    }
}