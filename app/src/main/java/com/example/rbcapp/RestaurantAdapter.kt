package com.example.rbcapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.restaurant_entry.view.*

/**
 * An adapter class to enable the restaurants to be displayed as a gridview
 */
class RestaurantAdapter : BaseAdapter {
    var foodsList = ArrayList<Restaurant>()
    var context: Context? = null

    constructor(context: Context, foodsList: ArrayList<Restaurant>) : super() {
        this.context = context
        this.foodsList = foodsList
    }

    override fun getCount(): Int {
        return foodsList.size
    }

    override fun getItem(position: Int): Any {
        return foodsList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val food = this.foodsList[position]

        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var foodView = inflator.inflate(R.layout.restaurant_entry, null)
        foodView.tvName.text = food.name
        foodView.tvAddress.text = food.address

        return foodView
    }
}
