package com.example.rbcapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import com.jieheng.yelpfusion.api.YelpFusionApi

/**
 * MainActivity class
 */
class MainActivity : AppCompatActivity() {

    companion object {
        var restaurantList = ArrayList<Restaurant>()
        lateinit var selectedRestaurant: Restaurant
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        YelpFusionApi.init(getString(R.string.api_key))
    }

}
