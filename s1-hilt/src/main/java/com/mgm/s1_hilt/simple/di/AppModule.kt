package com.mgm.s1_hilt.simple.di

import com.mgm.s1_hilt.simple.NAMED_APP_INFO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

/**
 * Created by Majid-Golmoradi on 8/20/2022.
 * Email: golmoradi.majid@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Named(NAMED_APP_INFO)
    fun provideInfo() = "Sample app info from appModule"
}