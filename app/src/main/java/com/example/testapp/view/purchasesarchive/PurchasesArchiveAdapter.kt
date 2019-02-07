package com.example.testapp.view.purchasesarchive

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testapp.R
import kotlinx.android.synthetic.main.item_archive_list.view.*

class PurchasesArchiveAdapter (val listener: (position: Int) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var myList: List<String>? = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_archive_list, parent, false))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        myList!![position].let {
            (holder as ViewHolder).bind(myList!![position])
        }
    }

    override fun getItemCount(): Int {
        return myList!!.size
    }


    fun setData(myChildrenList: List<String>?){
        this.myList = myChildrenList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.rootView.setOnClickListener { listener(adapterPosition) }
        }

        fun bind(date: String){
            itemView.dateTv.text = date
        }
    }
}