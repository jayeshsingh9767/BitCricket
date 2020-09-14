package com.example.bitcricket.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.bitcricket.R
import com.example.bitcricket.adaptor.PlayersAdaptor
import com.example.bitcricket.model.Players
import kotlinx.android.synthetic.main.players_list_fragment.*

class PlayersList : Fragment() {
    lateinit var adaptor: PlayersAdaptor

    companion object {
        fun newInstance() = PlayersList()
    }

    private lateinit var viewModel: PlayersListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adaptor = PlayersAdaptor(ArrayList<Players>())
        return inflater.inflate(R.layout.players_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PlayersListViewModel::class.java)
        // TODO: Use the ViewModel
        val player_data = ArrayList<Players>()
        player_data.add(
            Players(
                "Rahul Dravid",
                "Batsman",
                65.3f,
                50,
                28,
                6,
                4,
                12,
                "RCB",
                "India",
                "https://m.cricbuzz.com/a/img/v1/192x192/i1/c156286/rahul-dravid.jpg"
            )
        )
        player_data.add(
            Players(
                "Amit Sharma",
                "Bowler",
                58.2f,
                12,
                28,
                2,
                1,
                1,
                "RCB",
                "India",
                "https://www.cricdost.com/blog/wp-content/uploads/2019/01/rohit-sharma.png"
            )
        )

        player_data.add(
            Players(
                "MS Dhoni",
                "Wicketkeeper",
                57.7f,
                8,
                35,
                15,
                5,
                15,
                "CSK",
                "India",
                "https://www.vippng.com/png/detail/106-1067517_ms-dhoni-hd-photos-wallpapers-download-photos-of.png"
            )
        )


        player_list.adapter = adaptor
        player_list.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        adaptor.onItemClick = {
            Log.d("info1", "Click on item ${it.name}")
            val action = PlayersListDirections.actionPlayersListToPlayerDetails(it)
            findNavController().navigate(action)
        }
        if (adaptor.itemCount == 0) {
            adaptor.addData(player_data)
        }

    }

}
