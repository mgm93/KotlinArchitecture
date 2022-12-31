package com.mgm.s2_flow.retrofit.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgm.s2_flow.retrofit.model.ResponseMovies
import com.mgm.s2_flow.utils.MyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 12/31/2022.
 * Email: golmoradi.majid@gmail.com
 */
@HiltViewModel
class MovieViewModelParallel @Inject constructor(private val repository: MovieRepositoryMulti) :
    ViewModel() {
        val moviesList = MutableLiveData<MyResponse<List<ResponseMovies.Data>>>()

    fun getMoviesList() = viewModelScope.launch {
        moviesList.postValue(MyResponse.loading())
        repository.getMoviesPage1().zip(repository.getMoviesPage2()) { page1, page2 ->
            val movieFromApi = mutableListOf<ResponseMovies.Data>()
            movieFromApi.addAll(page1.body()!!.data)
            movieFromApi.addAll(page2.body()!!.data)
            return@zip movieFromApi
        }.catch { moviesList.postValue(MyResponse.error(it.message.toString())) }
            .flowOn(Dispatchers.IO)
            .collect{
                moviesList.postValue(MyResponse.success(it))
            }
    }
}