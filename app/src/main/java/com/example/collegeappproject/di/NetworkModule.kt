package com.example.collegeappproject.di

import com.example.collegeappproject.features.webScrapping.WebScrapingApi
import com.example.collegeappproject.features.webScrapping.WebScrappingRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideRetrofit() = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl("https://www.msit.in")
        .build()

    @Singleton
    @Provides
    fun provideWebRepoInstance(retrofit: Retrofit)= retrofit
        .create(WebScrapingApi::class.java)



}