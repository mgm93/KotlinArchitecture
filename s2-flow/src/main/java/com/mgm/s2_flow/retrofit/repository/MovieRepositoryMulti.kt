package com.mgm.s2_flow.retrofit.repository

import com.mgm.s2_flow.retrofit.api.MovieApi
import com.mgm.s2_flow.retrofit.model.ResponseMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 12/31/2022.
 * Email: golmoradi.majid@gmail.com
 */
class MovieRepositoryMulti @Inject constructor(private val api: MovieApi) {

    suspend fun getMoviesPage1():Flow<Response<ResponseMovies>>{
        return flow { emit(api.getMoviesList(1)) }
    }

    suspend fun getMoviesPage2(): Flow<Response<ResponseMovies>>{
        return flow { emit(api.getMoviesList(2)) }
    }
}