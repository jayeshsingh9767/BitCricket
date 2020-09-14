package com.example.bitcricket.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bitcricket.R
import com.example.bitcricket.model.Matches

class MatchsAdaptor(val nList: ArrayList<Matches>): RecyclerView.Adapter<MatchsAdaptor.ViewHolder>() {
    lateinit var context: Context
    var listFilter:MutableList<Matches> = nList
    var onItemClick: ((Matches) -> Unit)? = null

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val team1 = itemView.findViewById<TextView>(R.id.team1)
        val team2 = itemView.findViewById<TextView>(R.id.team2)
        val league = itemView.findViewById<TextView>(R.id.league_name)
        val date = itemView.findViewById<TextView>(R.id.date)
        val status = itemView.findViewById<TextView>(R.id.status)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(nList[adapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.match_card, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listFilter.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listFilter[position]
        holder.league.text = data.league
        holder.team1.text = data.team1
        holder.team2.text = data.team2
        holder.date.text = data.date.toString()
        holder.status.text = data.status
    }

    fun addData(listItems: ArrayList<Matches>) {
        var size = this.listFilter.size
        this.listFilter.addAll(listItems)
        var sizeNew = this.listFilter.size
        notifyItemRangeChanged(size, sizeNew)
    }

    fun filterByCategory(newData: ArrayList<Matches>){
        listFilter.clear()
        listFilter = newData
        notifyDataSetChanged()
    }
}