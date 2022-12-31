package com.mgm.s2_flow.retrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgm.s2_flow.retrofit.model.ResponseMovies
import com.mgm.s2_flow.retrofit.repository.MovieRepositoryMulti
import com.mgm.s2_flow.utils.MyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 12/31/2022.
 * Email: golmoradi.majid@gmail.com
 */
@FlowPreview
@HiltViewModel
class MovieViewModelSeries @Inject constructor(private val repository: MovieRepositoryMulti) :
    ViewModel() {
    val moviesList = MutableLiveData<MyResponse<List<ResponseMovies.Data>>>()

    fun getMoviesList() = viewModelScope.launch {
        moviesList.postValue(MyResponse.loading())
        val moviesFromApi = mutableListOf<ResponseMovies.Data>()
        repository.getMoviesPage1().flatMapConcat { page1 ->
            moviesFromApi.addAll(page1.body()!!.data)
            repository.getMoviesPage2()
        }.catch { moviesList.postValue(MyResponse.error(it.message.toString())) }
            .flowOn(Dispatchers.IO)
            .collect { page2 ->
                moviesFromApi.addAll(page2.body()!!.data)
                moviesList.postValue(MyResponse.success(moviesFromApi))
            }
    }
}