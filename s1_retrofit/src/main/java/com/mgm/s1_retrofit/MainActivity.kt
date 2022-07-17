package com.mgm.s1_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mgm.s1_retrofit.databinding.ActivityMainBinding
import com.mgm.s1_retrofit.databinding.ItemMoviesBinding
import com.mgm.s1_retrofit.models.ResponseMovies
import com.mgm.s1_retrofit.servers.ApiClient
import com.mgm.s1_retrofit.servers.ApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityMainBinding
    //Adapter
    private val moviesAdapter by lazy { MoviesAdapter() }
    //ApiService
    private val apiServices by lazy {
        ApiClient().getClient().create(ApiServices::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //initView
        binding.apply {
            prgbMainActivity.visibility = View.VISIBLE
            recyclerMainActivity.visibility = View.GONE
            //getData
            getData()
        }
    }

    private fun getData(){
        val callMovies = apiServices.getMovies(1)
        callMovies.enqueue(object : Callback<ResponseMovies>{
            override fun onResponse(
                call: Call<ResponseMovies>,
                response: Response<ResponseMovies>
            ) {
                binding.prgbMainActivity.visibility = View.GONE
                if (response.isSuccessful){
                    response.body()?.let { itBody ->
                        itBody.data?.let {itData ->
                            if (itData.isNotEmpty()) {
                                moviesAdapter.differ.submitList(itData)
                                binding.recyclerMainActivity.apply {
                                    layoutManager = LinearLayoutManager(this@MainActivity)
                                   adapter = moviesAdapter
                                    visibility = View.VISIBLE
                                }
                            }
                        }
                    }
                }


            }

            override fun onFailure(call: Call<ResponseMovies>, t: Throwable) {
                binding.prgbMainActivity.visibility = View.GONE
                Log.e("onFailure", "Err : ${t.message}")
            }

        })
    }
}