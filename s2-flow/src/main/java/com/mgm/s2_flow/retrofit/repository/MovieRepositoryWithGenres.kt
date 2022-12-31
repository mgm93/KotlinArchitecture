package com.mgm.s2_flow.retrofit.repository

import com.mgm.s2_flow.retrofit.api.MovieApi
import com.mgm.s2_flow.retrofit.model.ResponseGenresList
import com.mgm.s2_flow.retrofit.model.ResponseMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 12/31/2022.
 * Email: golmoradi.majid@gmail.com
 */
class MovieRepositoryWithGenres @Inject constructor(private val api: MovieApi) {

    fun getMovies() : Flow<Response<ResponseMovies>> = flow {
        emit(api.getMoviesList(1))
    }

    fun getGenres(): Flow<Response<ResponseGenresList>> = flow {
        emit(api.getGenresList())
    }
}