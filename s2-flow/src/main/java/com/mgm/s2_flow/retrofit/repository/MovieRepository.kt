package com.mgm.s2_flow.retrofit.repository

import com.mgm.s2_flow.retrofit.api.MovieApi
import com.mgm.s2_flow.retrofit.model.ResponseMovies
import com.mgm.s2_flow.utils.MyResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 12/29/2022.
 * Email: golmoradi.majid@gmail.com
 */
class MovieRepository @Inject constructor(private val api: MovieApi) {

    suspend fun getMoviesList(): Flow<MyResponse<ResponseMovies>> {
        return flow {
            emit(MyResponse.loading())
            val list = api.getMoviesList(1)
            emit(MyResponse.success(list.body()))
        }.catch {emit(MyResponse.error(it.message.toString())) }
            .flowOn(Dispatchers.IO)
    }
}