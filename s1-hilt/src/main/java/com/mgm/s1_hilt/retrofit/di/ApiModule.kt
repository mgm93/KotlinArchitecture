package com.mgm.s1_hilt.retrofit.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mgm.s1_hilt.BASE_URL
import com.mgm.s1_hilt.NAMED_BODY
import com.mgm.s1_hilt.NAMED_HEADER
import com.mgm.s1_hilt.NETWORK_TIMEOUT
import com.mgm.s1_hilt.retrofit.api.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Majid-Golmoradi on 8/22/2022.
 * Email: golmoradi.majid@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideNetworkTimeOut() = NETWORK_TIMEOUT

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    @Named(NAMED_HEADER)
    fun provideHeaderInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.HEADERS
    }

    @Provides
    @Singleton
    @Named(NAMED_BODY)
    fun provideBodyInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun apiClient(
        time: Long,
        @Named(NAMED_HEADER) header: HttpLoggingInterceptor,
        @Named(NAMED_BODY) body: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .connectTimeout(time, TimeUnit.SECONDS)
        .readTimeout(time, TimeUnit.SECONDS)
        .writeTimeout(time, TimeUnit.SECONDS)
        .addInterceptor(header)
        .addInterceptor(body)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl:String, gson: Gson, client:OkHttpClient):ApiServices =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiServices::class.java)

}