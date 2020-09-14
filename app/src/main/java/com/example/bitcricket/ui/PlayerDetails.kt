package com.example.bitcricket.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.bitcricket.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.player_details_fragment.*

class PlayerDetails : Fragment() {
    lateinit var args: PlayerDetailsArgs
    companion object {
        fun newInstance() = PlayerDetails()
    }

    private lateinit var viewModel: PlayerDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        args = PlayerDetailsArgs.fromBundle(requireArguments())
        return inflater.inflate(R.layout.player_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PlayerDetailsViewModel::class.java)
        // TODO: Use the ViewModel

        name.text = args.playerData.name
        run_rate.text = args.playerData.run_rate.toString()
        type.text = args.playerData.type
        wickets.text = args.playerData.wickets.toString()
        fours.text = args.playerData.fours.toString()
        sixes.text = args.playerData.sixes.toString()
        country.text = args.playerData.country.toString()
        age.text = args.playerData.age.toString()

        Picasso.get()
            .load(args.playerData.image.toString())
            .placeholder(R.drawable.loading)
            .error(R.drawable.error)
            .into(logo)

    }

}
