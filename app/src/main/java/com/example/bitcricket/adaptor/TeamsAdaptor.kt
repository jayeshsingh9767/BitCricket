package com.example.bitcricket.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bitcricket.R
import com.example.bitcricket.model.Teams

class TeamsAdaptor(val nList: ArrayList<Teams>):RecyclerView.Adapter<TeamsAdaptor.ViewHolder>(){
    lateinit var context: Context
    var listFilter:MutableList<Teams> = nList
    var onItemClick: ((Teams) -> Unit)? = null

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val team_name =  itemView.findViewById<TextView>(R.id.player_name)
        val wins =  itemView.findViewById<TextView>(R.id.fours)
        val looses =  itemView.findViewById<TextView>(R.id.sixes)
        val rank =  itemView.findViewById<TextView>(R.id.rank)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(nList[adapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.team_card, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listFilter.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listFilter[position]
        holder.team_name.text = data.name
        holder.wins.text = "Wins: "+data.wins.toString()
        holder.looses.text = "Looses: "+data.looses.toString()
        holder.rank.text = data.rank.toString()
//        holder.itemView.setOnClickListener {
//            Log.d("info1","Click on item ${data.name}")
//            val action =TeamsFragmentDirections.actionNavigationHomeToTeamProfile(data)
//
//        }


    }
    fun addData(listItems: ArrayList<Teams>) {
        var size = this.listFilter.size
        this.listFilter.addAll(listItems)
        var sizeNew = this.listFilter.size
        notifyItemRangeChanged(size, sizeNew)
    }
}