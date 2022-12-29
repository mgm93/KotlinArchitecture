package com.mgm.s2_flow.retrofit

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mgm.s2_flow.databinding.ActivityRetrofitBinding
import com.mgm.s2_flow.retrofit.adapter.ListMoviesAdapter
import com.mgm.s2_flow.retrofit.viewmodel.RetrofitViewModel
import com.mgm.s2_flow.utils.MyResponse
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RetrofitActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityRetrofitBinding
    private val TAG = "RetrofitActivity"

    @Inject
    lateinit var moviesAdapter: ListMoviesAdapter

    private val viewModel: RetrofitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //call method
            viewModel.getMoviesList()

            viewModel.listMovies.observe(this@RetrofitActivity) {
                when (it.status) {
                    MyResponse.Status.LOADING -> {
                        loading.visibility = View.VISIBLE
                    }
                    MyResponse.Status.SUCCESS -> {
                        loading.visibility = View.GONE
                        //setAdapter data
                        moviesAdapter.differ.submitList(it.data!!.data)
                        //recycler
                        listMovies.apply {
                            layoutManager = LinearLayoutManager(this@RetrofitActivity)
                            adapter = moviesAdapter
                        }
                    }
                    MyResponse.Status.ERROR -> {
                        loading.visibility = View.GONE
                        Toast.makeText(this@RetrofitActivity, it.message, Toast.LENGTH_SHORT).show()
                        Log.e(TAG, "onCreate: "+it.message)
                    }
                }
            }
        }
    }
}