package com.example.rbcapp
import android.util.Log
import com.jieheng.yelpfusion.api.YelpFusionApi
import com.jieheng.yelpfusion.request.BusinessSearchRequest
import io.reactivex.schedulers.Schedulers

/**
 *
 * A class to abstract the Yelp API query and data structure logic the from the SearchPageFrag Fragment
 */
object APIQuery {

    /**
     * 1. Makes a query using the Yelp Fusion API and supplied search term
     * 2. Parses the results and creates Restaurant objects
     * 3. Adds Restaurant objects to the restaurantList data structure
     * @param searchTerm the search term provided by the user
     * @return the new size of the group.
     */
    fun makeSearchQuery(searchTerm: String) {

        val request = BusinessSearchRequest(
            term = searchTerm,
            latitude = 1.3521,
            longitude = 103.8198,
            limit = 10,
            categories = "restaurants"
        )

        YelpFusionApi.get()
            .getBusinessSearch(request)
            .subscribeOn(Schedulers.io())
            .subscribe({
                MainActivity.restaurantList = ArrayList<Restaurant>()

                for (item in it.businesses) {
                    var restaurant =
                        Restaurant( item.id)
                    restaurant.img = item.image_url
                    restaurant.name = item.name
                    restaurant.address = item.location.address1
                    MainActivity.restaurantList.add(restaurant)
                }

            }, {
                Log.d("API_CALL", "request failed")
            })
    }
}