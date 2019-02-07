package com.example.testapp.view.shoppinglist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testapp.R
import com.example.testapp.domain.model.Product
import kotlinx.android.synthetic.main.item_product.view.*

class ShoppingListAdapter (val listener: (position: Int) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var myList: List<Product> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        myList[position].let {
            (holder as ViewHolder).bind(myList!![position])
        }
    }

    override fun getItemCount(): Int {
        return myList.size
    }


    fun setData(products: List<Product>){
        this.myList = products
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.rootView.setOnClickListener { listener(adapterPosition) }
        }

        fun bind(product: Product){
            itemView.nameTv.text = product.name
            itemView.numberTv.text = product.number.toString()
            itemView.priceTv.text = product.price.toString()
        }
    }
}