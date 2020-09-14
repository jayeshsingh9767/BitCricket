package com.example.bitcricket.ui.dashboard

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitcricket.R
import com.example.bitcricket.adaptor.MatchsAdaptor
import com.example.bitcricket.model.Matches
import kotlinx.android.synthetic.main.fragment_matches.*
import java.util.*
import kotlin.collections.ArrayList

class MatchesFragment : Fragment() {
    val adaptor: MatchsAdaptor = MatchsAdaptor(ArrayList<Matches>())
    val TAG = "info1"
    private lateinit var dashboardViewModel: DashboardViewModel
    var oCategoryList: ArrayList<String>? = ArrayList<String>()
    val match_data = ArrayList<Matches>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_matches, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
//        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        setHasOptionsMenu(true)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        match_data.add(
            Matches(
                "RCB",
                "CSK",
                Date().toString(),
                "ODI",
                "IPL",
                "Completed",
                "RCB",
                "CSK",
                "Rohit Sharma",
                "Amit Pathak",
                "Rahul Sharma"
            )
        )

        match_data.add(
            Matches(
                "MI",
                "RCB",
                Date().toString(),
                "Test",
                "IPL",
                "Pending",
                "",
                "",
                "",
                "",
                ""
            )
        )

        match_data.add(
            Matches(
                "CSK",
                "DD",
                Date().toString(),
                "Test",
                "IPL",
                "Live",
                "CSK",
                "DD",
                "Ajay Singh",
                "Jack Simmons",
                "Jayesh Singh"
            )
        )

        match_data.add(
            Matches(
                "RR",
                "MI",
                Date().toString(),
                "Test",
                "IPL",
                "Live",
                "RR",
                "MI",
                "Ajay Singh",
                "Jack Simmons",
                "Jayesh Singh"
            )
        )

        match_list.adapter = adaptor
        match_list.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        adaptor.onItemClick = {
            Log.d("info1", "Click on item ${it.team1}")
            val action = MatchesFragmentDirections.actionNavigationDashboardToMatchSummary(it)
            findNavController().navigate(action)
        }
        if (adaptor.itemCount == 0) {
            adaptor.addData(match_data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.match_menu, menu)
//        var item: MenuItem = menu.findItem(R.id.search_btn)!!
//        searchView = MenuItemCompat.getActionView(item) as androidx.appcompat.widget.SearchView
//        search(searchView)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter_category -> {
                selectCategory()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    fun selectCategory() {
        // setup the alert builder
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Choose Category")

// add a checkbox list
//        oCategoryList = socList.toTypedArray()
        oCategoryList?.clear()
        oCategoryList?.add("Live")
        oCategoryList?.add("Pending")
        oCategoryList?.add("Completed")

        val checkedItems = BooleanArray(oCategoryList!!.size)
//
//        oCategoryList!!.forEachIndexed { index, element ->
//            checkedItems[index] = false
//        }
        builder.setMultiChoiceItems(oCategoryList!!.toTypedArray(), checkedItems) { dialog, which, isChecked ->
            // user checked or unchecked a box
            Log.d(TAG, "parameters are $dialog and $which and $isChecked")
        }

// add OK and Cancel buttons
        builder.setPositiveButton("OK") { dialog, which ->
            Log.d(TAG, "parameters are $dialog and $which")
            Log.d(TAG, "booleans are ${checkedItems[0]}")
            val selectedCategories = getCheckedCategory(checkedItems)
            Log.d(TAG, "Selected categories is $selectedCategories")
            if(selectedCategories.size != 0) {
                val filteredData = match_data.filter {
                    it.status in selectedCategories
                }
                adaptor.filterByCategory(filteredData as ArrayList<Matches>)
            }
        }
        builder.setNegativeButton("Cancel", null)

// create and show the alert dialog
        val dialog = builder.create()
        dialog.show()
    }

    fun getCheckedCategory(check: BooleanArray): ArrayList<String>{
        val res = ArrayList<String>()
        check.forEachIndexed {index, it ->
            if(it){
                res.add(oCategoryList!![index])
            }
        }
        return res
    }



}
