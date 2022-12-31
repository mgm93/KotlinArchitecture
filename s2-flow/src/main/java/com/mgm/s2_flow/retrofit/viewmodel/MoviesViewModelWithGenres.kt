package com.mgm.s2_flow.retrofit.viewmodel

import androidx.lifecycle.ViewModel
import com.mgm.s2_flow.retrofit.model.ResponseGenresList
import com.mgm.s2_flow.retrofit.model.ResponseMovies
import com.mgm.s2_flow.retrofit.repository.MovieRepositoryWithGenres
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 12/31/2022.
 * Email: golmoradi.majid@gmail.com
 */
@HiltViewModel
class MoviesViewModelWithGenres @Inject constructor(private val repository: MovieRepositoryWithGenres) : ViewModel() {
    val moviesFlow = repository.getMovies()
    val genresFlow = repository.getGenres()

    fun loadMoviesAndGenres(): Flow<List<Pair<ResponseMovies.Data, ResponseGenresList.ResponseGenresListItem>>> =
        combine(moviesFlow , genresFlow){
            movieList: Response<ResponseMovies>, genresList : Response<ResponseGenresList> ->
            val movies = movieList.body()?.data
            val genres = genresList.body()!!
            return@combine movies?.zip(genres)!!
        }
}
