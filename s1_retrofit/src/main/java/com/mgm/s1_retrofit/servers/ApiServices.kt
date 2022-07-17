package com.mgm.s1_retrofit.servers

import com.mgm.s1_retrofit.models.ResponseMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Majid-Golmoradi on 7/17/2022.
 * Email: golmoradi.majid@gmail.com
 */
interface ApiServices {
    @GET("movies")
    fun getMovies(@Query("page") page:Int): Call<ResponseMovies>
}