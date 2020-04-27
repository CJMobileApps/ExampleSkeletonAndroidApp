package com.cjmobileapps.exampleskeletonandroidapp.network.model

data class Player(
    val id: String,
    val firstName: String,
    val lastName: String,
    val favoriteSubject: String,
    val position: Int,
    var positionName: String? = null,
    val imageUrl: String,
    val yearsPlayed: List<Int>
) {
    val fullName: String
        get() = "$firstName $lastName"
}