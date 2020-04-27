package com.cjmobileapps.exampleskeletonandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cjmobileapps.exampleskeletonandroidapp.network.NetworkFactory
import com.cjmobileapps.exampleskeletonandroidapp.viewmodel.MainViewModel
import com.cjmobileapps.exampleskeletonandroidapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = NetworkFactory.getApi()
        val mainService = MainService(api)
        val mainViewModel = ViewModelProviders.of(this, ViewModelFactory(mainService))[MainViewModel::class.java]

        mainActivity_players.layoutManager = LinearLayoutManager(this)

        mainViewModel.playersMutableLiveData.observe(this, Observer { players ->
            val mainAdapter = MainAdapter(players)
            mainActivity_players.adapter = mainAdapter
        })

        mainViewModel.getPlayers()
    }


}
