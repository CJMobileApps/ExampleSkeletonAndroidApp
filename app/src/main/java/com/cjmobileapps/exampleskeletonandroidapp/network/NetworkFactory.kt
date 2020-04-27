package com.cjmobileapps.exampleskeletonandroidapp.network

import android.content.Context
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

class NetworkFactory {

    companion object {

        //OkHttp
        const val HTTP_CACHE_DIR = "okhttp_cache"
        const val HTTP_CACHE_SIZE: Long = 10 * 1024 * 1024 //10 MB

        //NetworkCache
        const val CACHE_CONTROL = "Cache-Control"

        //Create a cache object part 1.
        fun httpCacheDirectory(context: Context): File {
            return File(context.getCacheDir(), HTTP_CACHE_DIR)
        }

        //Create a cache object part 2.
        fun cache(httpCacheDirectory: File): Cache {
            return Cache(httpCacheDirectory, HTTP_CACHE_SIZE)
        }

        //Create a network cache interceptor, setting the max age to 1 minute
        fun networkCacheInterceptor(): Interceptor {
            return object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val response = chain.proceed(chain.request())

                    val cacheControl = CacheControl.Builder()
                        .maxAge(1, TimeUnit.MINUTES)
                        .build()

                    return response.newBuilder()
                        .header(CACHE_CONTROL, cacheControl.toString()).build()
                }
            }
        }

        //Create a logging interceptor
        fun loggingInterceptor(): HttpLoggingInterceptor {
            val loggingInterceptor = HttpLoggingInterceptor()
            return loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        //Create the httpClient, configure it
        // with cache, network cache interceptor and logging interceptor
        fun okHttpClient(
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