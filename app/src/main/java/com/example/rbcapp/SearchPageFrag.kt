package com.example.rbcapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rbcapp.MainActivity.Companion.restaurantList
import com.example.rbcapp.MainActivity.Companion.selectedRestaurant
import kotlinx.android.synthetic.main.fragment_first.view.*

/**
 * The default page for the user to search for restaurants
 *
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SearchPageFrag : Fragment() {

    private var root: View? = null
    var adapter: RestaurantAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        root =  inflater.inflate(R.layout.fragment_first, container, false)
        return root
    }

    fun renderGrid() {
        adapter = RestaurantAdapter(requireContext(), restaurantList)
        root!!.restaurantGrid.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var sorted = false

        adapter = RestaurantAdapter(requireContext(), restaurantList)
        root!!.restaurantGrid.adapter = adapter

        view.findViewById<EditText>(R.id.searchBar).addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                APIQuery.makeSearchQuery(view.findViewById<EditText>(R.id.searchBar).text.toString())
                renderGrid()
            }
        })

        view.findViewById<Button>(R.id.sort).setOnClickListener {
            if (!sorted) {
                restaurantList = ArrayList(restaurantList.sortedWith(compareBy { it.name }))
                sorted = true;
            } else {
                restaurantList = ArrayList(restaurantList.reversed())
            }
            renderGrid()
        }

        root!!.restaurantGrid.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selected = parent.getItemAtPosition(position) as Restaurant
                selectedRestaurant = selected
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        }

    }

}
