package com.cjmobileapps.exampleskeletonandroidapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cjmobileapps.exampleskeletonandroidapp.network.model.Player
import kotlinx.android.synthetic.main.row_player.view.*

class MainAdapter(private val players: List<Player>) : RecyclerView.Adapter<MainAdapter.MainAdapterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MainAdapterHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_player, parent, false))

    override fun getItemCount() = players.size

    override fun onBindViewHolder(holder: MainAdapterHolder, position: Int) {
        holder.bind(players[position])
    }

    inner class MainAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(player: Player) {
            itemView.playerRow_name.text = player.fullName
        }
    }
}
