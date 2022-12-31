package com.mgm.s2_flow.retrofit.api

import com.mgm.s2_flow.retrofit.model.ResponseGenresList
import com.mgm.s2_flow.retrofit.model.ResponseMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Majid-Golmoradi on 12/29/2022.
 * Email: golmoradi.majid@gmail.com
 */
interface MovieApi {

    @GET("movies")
    suspend fun getMoviesList(@Query("page") page: Int) : Response<ResponseMovies>

    @GET("genres")
    suspend fun getGenresList() : Response<ResponseGenresList>
}