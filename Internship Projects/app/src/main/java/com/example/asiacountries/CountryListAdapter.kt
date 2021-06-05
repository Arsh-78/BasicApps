package com.example.asiacountries

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CountryListAdapter(): RecyclerView.Adapter<CountryViewHolder>() {

    private val items : ArrayList<Country> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country,parent,false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val currentItem =items[position]
        holder.titleView.text= currentItem.cName

    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun updateCountry(updatedCountry: ArrayList<Country>){
        items.clear()
        items.addAll(updatedCountry)
        notifyDataSetChanged()
    }


}
class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
val titleView : TextView = itemView.findViewById((R.id.name))
    val imageView : ImageView = itemView.findViewById(R.id.flag)
}