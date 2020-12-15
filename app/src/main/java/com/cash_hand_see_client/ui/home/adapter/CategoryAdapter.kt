package com.cash_hand_see_client.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cash_hand_see_client.R
import com.cash_hand_see_client.ui.home.bean.BooksComics
import kotlinx.android.synthetic.main.category_item.view.*


class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    var list: MutableList<BooksComics> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.category_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(list[position].cover).into(holder.itemImage)
        holder.itemName.text = list[position].name
        holder.itemDesc.text = list[position].latest
        holder.itemTime.text = list[position].time

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.item_image
        val itemName: TextView = itemView.item_name
        val itemDesc: TextView = itemView.item_desc
        val itemTime: TextView = itemView.item_time
    }

    fun setData(list: MutableList<BooksComics>) {
        this.list = list
        notifyDataSetChanged()
    }
}