package com.mgm.s1_hilt.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mgm.s1_hilt.databinding.ActivityMoviesBinding
import com.mgm.s1_hilt.retrofit.api.ApiServices
import com.mgm.s1_hilt.retrofit.models.ResponseMovies
import com.mgm.s1_hilt.retrofit.repository.ApiRepository
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {
    //binding
    private lateinit var binding: ActivityMoviesBinding

    //MoviesAdapter
    @Inject
    lateinit var moviesAdapter: MoviesAdapter
    //ApiRepository
    @Inject
    lateinit var apiRepository: ApiRepository

    private val TAG = "MoviesActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            progressbar.visibility = View.VISIBLE
            apiRepository.getAllMovies().enqueue(object : Callback<ResponseMovies>{
                override fun onResponse(
                    call: Call<ResponseMovies>,
                    response: Response<ResponseMovies>
                ) {
                    progressbar.visibility = View.GONE
                    if (response.isSuccessful) {
                        response.body()?.let { itBody ->
                            itBody.data?.let {itData ->
                                if (itData.isNotEmpty()) {
                                    moviesAdapter.differ.submitList(itData)
                                        recycler.apply {
                                        layoutManager = LinearLayoutManager(this@MoviesActivity)
                                        adapter = moviesAdapter
                                        visibility = View.VISIBLE
                                    }
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseMovies>, t: Throwable) {
                    progressbar.visibility = View.GONE
                    Log.e(TAG, t.message.toString())
                }

            })
        }
    }
}