package com.mgm.s1_hilt.retrofit.repository

import com.mgm.s1_hilt.retrofit.api.ApiServices
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 8/22/2022.
 * Email: golmoradi.majid@gmail.com
 */

@ActivityScoped
class ApiRepository @Inject constructor(private val apiServices: ApiServices) {
    fun getAllMovies()= apiServices.getMoviesList()
}