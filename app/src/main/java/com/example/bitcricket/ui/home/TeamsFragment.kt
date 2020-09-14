package com.example.bitcricket.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitcricket.R
import com.example.bitcricket.adaptor.TeamsAdaptor
import com.example.bitcricket.model.Teams

class TeamsFragment : Fragment() {
    lateinit var root: View
    private lateinit var homeViewModel: HomeViewModel
    val adaptor = TeamsAdaptor(ArrayList<Teams>())
    lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()

        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_team, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val team_list: RecyclerView = root.findViewById(R.id.team_list)
        val team_data = ArrayList<Teams>()
        team_data.add(
            Teams(
                "RCB",
                8,
                5,
                3,
                "IPL",
                0,
                "Pepsi Co",
                15,
                "https://www.pngfind.com/pngs/m/672-6729592_ipl-team-logo-png-royal-challengers-bangalore-rcb.png"
            )
        )
        team_data.add(
            Teams(
                "CSK",
                7,
                2,
                2,
                "IPL",
                0,
                "Pepsi Co",
                15,
                "https://www.pngfind.com/pngs/m/672-6729592_ipl-team-logo-png-royal-challengers-bangalore-rcb.png"
            )
        )
        team_data.add(Teams("Mumbai Indian",
            9,
            2,
            1,
            "IPL",
            0,
            "Pepsi Co",
            15,
            "https://www.pngfind.com/pngs/m/672-6729592_ipl-team-logo-png-royal-challengers-bangalore-rcb.png"))
        team_data.add(Teams("Royal Challenger",
            5,
            4,
            4,
            "IPL",
            0,
            "Pepsi Co",
            15,
            "https://www.pngfind.com/pngs/m/672-6729592_ipl-team-logo-png-royal-challengers-bangalore-rcb.png"))

        team_list.adapter = adaptor
        team_list.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//        val dividerItemDecoration = DividerItemDecoration(
//            team_list.context,
//            LinearLayoutManager.VERTICAL
//        )
//        team_list.addItemDecoration(dividerItemDecoration)
        adaptor.onItemClick = {
            Log.d("info1","Click on item ${it.name}")
            val action =TeamsFragmentDirections.actionNavigationHomeToTeamProfile(it)
            navController.navigate(action)
        }
        if (adaptor.itemCount == 0) {
            adaptor.addData(team_data)
        }


    }


}
