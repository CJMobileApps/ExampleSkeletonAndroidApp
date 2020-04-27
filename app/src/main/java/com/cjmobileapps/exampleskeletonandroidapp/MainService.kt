package com.cjmobileapps.exampleskeletonandroidapp

import com.cjmobileapps.exampleskeletonandroidapp.network.Api

class MainService(private val api: Api) {

    fun getPlayers() = api.getPlayers()

}