package com.mgm.s2_flow.retrofit.api

import com.mgm.s2_flow.retrofit.model.ResponseMovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Majid-Golmoradi on 12/29/2022.
 * Email: golmoradi.majid@gmail.com
 */
interface MovieApi {

    @GET("movies")
    suspend fun getMoviesList(@Query("page") page: Int) : Response<ResponseMovieList>
}