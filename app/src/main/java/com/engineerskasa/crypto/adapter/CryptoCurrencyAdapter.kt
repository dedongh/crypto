package com.engineerskasa.crypto.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.engineerskasa.crypto.R
import com.engineerskasa.crypto.model.CryptoCurrency

class CryptoCurrencyAdapter(cryptocurrencies: List<CryptoCurrency>):RecyclerView.Adapter<CryptoCurrencyVH>() {
    private var cryptocurrenciesList = ArrayList<CryptoCurrency>()

    init {
        this.cryptocurrenciesList =  cryptocurrencies as ArrayList<CryptoCurrency>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoCurrencyVH {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cryptocurrency_list_item,
            parent, false)
        return CryptoCurrencyVH(itemView)
    }

    override fun onBindViewHolder(holder: CryptoCurrencyVH, position: Int) {
        val cryptocurrencyItem = cryptocurrenciesList[position]
        holder.cryptocurrencyListItem(cryptocurrencyItem)
    }

    override fun getItemCount(): Int {
        return cryptocurrenciesList.size
    }

    fun addCryptocurrencies(cryptocurrencies: List<CryptoCurrency>){
        val initPosition = cryptocurrenciesList.size
        cryptocurrenciesList.addAll(cryptocurrencies)
        notifyItemRangeInserted(initPosition, cryptocurrenciesList.size)
    }
}

class CryptoCurrencyVH(itemView: View): RecyclerView.ViewHolder(itemView) {
    var cryptocurrencyId = itemView.findViewById<TextView>(R.id.cryptocurrency_id)

    fun cryptocurrencyListItem(cryptocurrencyItem: CryptoCurrency) {
        cryptocurrencyId.text = cryptocurrencyItem.id
    }
}
