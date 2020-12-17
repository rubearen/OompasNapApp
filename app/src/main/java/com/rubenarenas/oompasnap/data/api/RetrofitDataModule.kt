package com.rubenarenas.oompasnap.data.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"

val dataModule = module {
    factory<Gson> {
        GsonBuilder().setDateFormat(DATE_FORMAT).create()
    }
    factory { provideOkHttpClient() }
    factory { provideAPIService(get()) }
    single { provideRetrofit(get(), get()) }
}

private fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder().build()

private fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
    Retrofit.Builder().apply {
        baseUrl(Api.BASE_URL)
        client(okHttpClient)
        addConverterFactory(GsonConverterFactory.create(gson))
    }.build()

private fun provideAPIService(retrofit: Retrofit): Api =
    retrofit.create(Api::class.java)

