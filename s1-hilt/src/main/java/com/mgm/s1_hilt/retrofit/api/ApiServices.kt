package com.mgm.s1_hilt.retrofit.api

import com.mgm.s1_hilt.retrofit.models.ResponseMovies
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Majid-Golmoradi on 8/22/2022.
 * Email: golmoradi.majid@gmail.com
 */
interface ApiServices {
    @GET("movies")
    fun getMoviesList():Call<ResponseMovies>
}