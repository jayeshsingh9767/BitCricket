package com.example.bitcricket.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bitcricket.R
import com.example.bitcricket.model.Players

class PlayersAdaptor(val nList: ArrayList<Players>): RecyclerView.Adapter<PlayersAdaptor.ViewHolder>() {
    lateinit var context: Context
    var listFilter:MutableList<Players> = nList
    var onItemClick: ((Players) -> Unit)? = null

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val player_name =  itemView.findViewById<TextView>(R.id.player_name)
        val fours =  itemView.findViewById<TextView>(R.id.fours)
        val sixes =  itemView.findViewById<TextView>(R.id.sixes)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(nList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.player_card, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listFilter.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listFilter[position]
        holder.player_name.text = data.name
        holder.fours.text = data.fours.toString()+" Fours"
        holder.sixes.text = data.sixes.toString()+" Sixes"
    }

    fun addData(listItems: ArrayList<Players>) {
        var size = this.listFilter.size
        this.listFilter.addAll(listItems)
        var sizeNew = this.listFilter.size
        notifyItemRangeChanged(size, sizeNew)
    }
}