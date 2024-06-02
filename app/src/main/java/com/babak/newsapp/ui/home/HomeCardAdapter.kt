package com.babak.newsapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.babak.newsapp.databinding.CardItemBinding
import com.babak.newsapp.model.Article
import com.babak.newsapp.utils.imageLoad

class HomeCardAdapter:RecyclerView.Adapter<HomeCardAdapter.CardViewHolder>() {
    private val list = arrayListOf<Article>()
    inner class CardViewHolder(var cardItemBinding: CardItemBinding):RecyclerView.ViewHolder(cardItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view=CardItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = list[position]
        holder.cardItemBinding.authorName.text=item.author


        holder.cardItemBinding.cardText.text = item.content
        holder.cardItemBinding.magazinName.text=item.source.name
        holder.cardItemBinding.cardImage.imageLoad(item.urlToImage)
    }

    fun updateList(newList:List<Article>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}