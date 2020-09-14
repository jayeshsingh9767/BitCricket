package com.example.bitcricket.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitcricket.R
import com.example.bitcricket.adaptor.CountryAdaptor
import com.example.bitcricket.model.Country
import kotlinx.android.synthetic.main.fragment_countries.*

class CountriesFragment : Fragment() {
    val adaptor: CountryAdaptor = CountryAdaptor(ArrayList<Country>())
    lateinit var searchView: androidx.appcompat.widget.SearchView

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_countries, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
//        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        setHasOptionsMenu(true)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val data = ArrayList<Country>()

        data.add(Country(
            "India",
            "https://upload.wikimedia.org/wikipedia/en/thumb/4/41/Flag_of_India.svg/1200px-Flag_of_India.svg.png")
        )

        data.add(Country(
            "China",
            "https://cdn.webshopapp.com/shops/94414/files/53642776/china-flag-icon-free-download.jpg")
        )
        data.add(Country(
            "USA",
            "https://upload.wikimedia.org/wikipedia/en/thumb/6/6c/Us_flag_large_38_stars.png/1200px-Us_flag_large_38_stars.png")
        )
        data.add(Country(
            "United Kingdom",
            "https://upload.wikimedia.org/wikipedia/en/thumb/a/ae/Flag_of_the_United_Kingdom.svg/1200px-Flag_of_the_United_Kingdom.svg.png")
        )
        data.add(Country(
            "Brazil",
            "https://cdn.webshopapp.com/shops/94414/files/52123490/flag-of-brazil.jpg")
        )
        data.add(Country(
            "Russia",
            "https://upload.wikimedia.org/wikipedia/en/thumb/f/f3/Flag_of_Russia.svg/1200px-Flag_of_Russia.svg.png")
        )
        data.add(Country(
            "Canada",
            "https://image.shutterstock.com/image-vector/flag-canada-260nw-94948876.jpg")
        )
        data.add(Country(
            "France",
            "https://upload.wikimedia.org/wikipedia/en/thumb/c/c3/Flag_of_France.svg/1200px-Flag_of_France.svg.png")
        )
        country_list.adapter = adaptor
        country_list.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        adaptor.addData(data)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.country_menu, menu)
        var item: MenuItem = menu.findItem(R.id.search_btn)!!
        searchView = MenuItemCompat.getActionView(item) as androidx.appcompat.widget.SearchView
        search(searchView)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun search(searchView: androidx.appcompat.widget.SearchView) {
        var listUsers: ArrayList<Country>
        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("info1", "query changes $newText")

                adaptor.filter.filter(newText)
                return true
            }

        })
    }

}
