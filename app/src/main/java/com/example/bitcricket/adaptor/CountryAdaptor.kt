package com.example.bitcricket.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bitcricket.R
import com.example.bitcricket.model.Country
import com.squareup.picasso.Picasso

data class CountryAdaptor(val nList: ArrayList<Country>): RecyclerView.Adapter<CountryAdaptor.ViewHolder>(),
    Filterable {
    lateinit var context: Context
    var listFilter:MutableList<Country> = nList
    var onItemClick: ((Country) -> Unit)? = null

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name =  itemView.findViewById<TextView>(R.id.country_name)
        val img =  itemView.findViewById<ImageView>(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.country_card, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listFilter.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listFilter[position]
        holder.name.text = data.name
        Picasso.get()
            .load(data.flag.toString())
            .placeholder(R.drawable.loading)
            .error(R.drawable.error)
            .into(holder.img)
//        holder.img.text = data.fours.toString()+" Fours"
    }


    fun addData(listItems: ArrayList<Country>) {
        var size = this.listFilter.size
        this.listFilter.addAll(listItems)
        var sizeNew = this.listFilter.size
        notifyItemRangeChanged(size, sizeNew)
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                var charString: String = constraint.toString()
                if(charString.isEmpty()){
                    listFilter = nList
                }else{

                    var filteredList:MutableList<Country> = mutableListOf()
                    nList.forEach{
                        var message:String = ""
                        message += it.name!!

                        if(message.toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(it)
                        }
                    }
                    listFilter = filteredList

                }
                var filterResult: FilterResults = FilterResults()
                filterResult.values = listFilter
                return filterResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listFilter = results!!.values as MutableList<Country>
                notifyDataSetChanged()
            }

        }
    }
}