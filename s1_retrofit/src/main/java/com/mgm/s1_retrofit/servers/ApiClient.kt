package com.mgm.s1_retrofit.servers

import com.mgm.s1_retrofit.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
/**
 * Created by Majid-Golmoradi on 7/17/2022.
 * Email: golmoradi.majid@gmail.com
 */
class ApiClient {
    private lateinit var retrofit:Retrofit

    private val client = OkHttpClient.Builder()
        .connectTimeout(Constants.NETWORK_TIMEOUT,TimeUnit.SECONDS)
        .readTimeout(Constants.NETWORK_TIMEOUT,TimeUnit.SECONDS)
        .writeTimeout(Constants.NETWORK_TIMEOUT,TimeUnit.SECONDS)
        .build()

    fun getClient(): Retrofit {
         retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
             .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}