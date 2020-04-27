package com.cjmobileapps.exampleskeletonandroidapp.network

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkFactory {

    companion object {

        //Create a logging interceptor
        private fun loggingInterceptor(): HttpLoggingInterceptor {
            val loggingInterceptor = HttpLoggingInterceptor()
            return loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        //Create the httpClient, configure it
        // with cache, network cache interceptor and logging interceptor
        private fun okHttpClient(
                         loggingInterceptor: HttpLoggingInterceptor
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }

        private fun retrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://cjmobileapps.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient(loggingInterceptor()))
                .build()
        }

        private fun api(retrofit: Retrofit): Api {
            return retrofit.create(Api::class.java)
        }

        fun getApi(): Api {
            return api(
                retrofit()
            )
        }
    }
}
