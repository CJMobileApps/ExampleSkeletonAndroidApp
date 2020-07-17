package com.cjmobileapps.exampleskeletonandroidapp.network

import com.cjmobileapps.exampleskeletonandroidapp.network.model.Login
import com.cjmobileapps.exampleskeletonandroidapp.network.model.Player
import com.cjmobileapps.exampleskeletonandroidapp.network.model.Position
import com.cjmobileapps.exampleskeletonandroidapp.network.model.Token
import io.reactivex.Single
import retrofit2.http.*

interface Api {

    @GET("api/v2/quidditch/player")
    fun getPlayers(): Single<List<Player>>

    @GET("api/v2/quidditch/positions")
    fun getPositions(): Single<List<Position>>

//    String encoding = Base64.getEncoder().encodeToString(("test1:test1").getBytes(‌"UTF‌​-8"​));
    @Headers("Authorization: max-age=640000")
    @POST("auth/token/{blah-id}")
    fun login(@Header("Authorization") header: String, @Path("blah-id") blah: String, @Body login: Login): Single<Token>
}
