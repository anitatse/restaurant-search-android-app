package com.example.rbcapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.rbcapp.MainActivity.Companion.selectedRestaurant
import com.jieheng.yelpfusion.api.YelpFusionApi
import com.squareup.picasso.Picasso
import io.reactivex.schedulers.Schedulers

/**
 * The page to view information for the selected restaurant
 *
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RestaurantInfoFrag : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.name).text = selectedRestaurant.name
        view.findViewById<TextView>(R.id.address).text = selectedRestaurant.address

        // due to Asynchronous issues, this is not abstracted into the APIQuery class
        YelpFusionApi.get()
            .getBusinessReviews(selectedRestaurant.id)
            .subscribeOn(Schedulers.io())
            .subscribe({
                selectedRestaurant.review = it.reviews[0].text
                view.findViewById<TextView>(R.id.review).text = selectedRestaurant.review
            }, {
                Log.d("API_CALL", "request failed")
            })

        Picasso.with(context)
            .load(selectedRestaurant.img)
            .into(view.findViewById<ImageView>(R.id.imageView));

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}
