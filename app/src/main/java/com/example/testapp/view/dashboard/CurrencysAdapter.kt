package com.example.testapp.view.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.testapp.R
import com.example.testapp.mapping.currencyLogoRes

class CurrencysAdapter  (private var currencys: List<String>, private val context: Context) : BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ItemRowHolder
        if (convertView == null) {
            view = LayoutInflater.from(parent!!.context).inflate(R.layout.currency_item, parent, false)
            viewHolder = ItemRowHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ItemRowHolder
        }

        val scale = context.resources.displayMetrics.density
        val dpAsPixels = (48 * scale + 0.5f).toInt()

        val params = view.layoutParams
        params.height = dpAsPixels
        view.layoutParams = params

        viewHolder.title.text = currencys[position]
        viewHolder.picture.setImageResource(currencys[position].currencyLogoRes)

        return view
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return currencys.size
    }

    private class ItemRowHolder(row: View?) {

        val title: TextView = row?.findViewById(R.id.titleTv) as TextView
        val picture : ImageView = row?.findViewById(R.id.pictureIv) as ImageView
    }
}