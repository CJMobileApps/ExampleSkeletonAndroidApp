package com.cjmobileapps.exampleskeletonandroidapp.network

import com.cjmobileapps.exampleskeletonandroidapp.network.model.Player
import com.cjmobileapps.exampleskeletonandroidapp.network.model.Position
import io.reactivex.Single
import retrofit2.http.GET

interface Api {

    @GET("api/v2/quidditch/player")
    fun getPlayers(): Single<List<Player>>

    @GET("api/v2/quidditch/positions")
    fun getPositions(): Single<List<Position>>
}