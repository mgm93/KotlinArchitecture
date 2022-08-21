package com.mgm.s1_hilt.simple.di

import com.mgm.s1_hilt.NAMED_SITE_NAME
import com.mgm.s1_hilt.NAMED_USER_NAME
import com.mgm.s1_hilt.simple.di.qualifier.SiteName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Majid-Golmoradi on 8/20/2022.
 * Email: golmoradi.majid@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    @Named(NAMED_USER_NAME)
    fun provideUserName()= "Majid Golmoradi"

    @Provides
//    @SiteName
    @Named(NAMED_SITE_NAME)
    fun provideSiteName():String{
        return "gol.com"
    }
}