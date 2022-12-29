package com.mgm.s2_flow.retrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgm.s2_flow.retrofit.model.ResponseMovieList
import com.mgm.s2_flow.retrofit.repository.MovieRepository
import com.mgm.s2_flow.utils.MyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 12/29/2022.
 * Email: golmoradi.majid@gmail.com
 */
@HiltViewModel
class RetrofitViewModel @Inject constructor(private val repository: MovieRepository): ViewModel(){
    val listMovies = MutableLiveData<MyResponse<ResponseMovieList>>()

    fun getMoviesList() = viewModelScope.launch {
        repository.getMoviesList().collect{
            listMovies.postValue(it)
        }
    }
}