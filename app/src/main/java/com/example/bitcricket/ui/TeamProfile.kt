package com.example.bitcricket.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.example.bitcricket.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.team_profile_fragment.*

class TeamProfile : Fragment() {
    lateinit var args: TeamProfileArgs

    companion object {
        fun newInstance() = TeamProfile()
    }

    private lateinit var viewModel: TeamProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        args = TeamProfileArgs.fromBundle(requireArguments())
        return inflater.inflate(R.layout.team_profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        name.text = args.teamData.name
        league.text = args.teamData.league
        fours.text = args.teamData.wins.toString()
        sixes.text = args.teamData.looses.toString()
        draw.text = args.teamData.draw.toString()
        player.text = args.teamData.player_count.toString()
        sponsor.text = "Sponsored By " + args.teamData.sponsor
        Picasso.get()
            .load(args.teamData.logo.toString())
            .placeholder(R.drawable.loading)
            .error(R.drawable.error)
            .into(logo)
        Log.d("info1", args.teamData.logo)

        button.setOnClickListener {
            Log.d("info1", "Click on Player button")
            findNavController().navigate(R.id.playersList)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TeamProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
