package com.cjmobileapps.exampleskeletonandroidapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cjmobileapps.exampleskeletonandroidapp.MainService
import com.cjmobileapps.exampleskeletonandroidapp.network.model.Player
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val mainService: MainService) : ViewModel() {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    val playersMutableLiveData = MutableLiveData<List<Player>>()

    fun getPlayers() {
        compositeDisposable.add(getPlayersDisposable())
    }

    private fun getPlayersDisposable() : Disposable {
        return mainService.getPlayers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ players ->
                Log.d("HERE_", "players " + players)
                playersMutableLiveData.value = players
            }, { error ->
                Log.e("HERE_","error " + error)
            })
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}
