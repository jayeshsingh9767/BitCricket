package com.example.bitcricket.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.bitcricket.R
import kotlinx.android.synthetic.main.match_summary_fragment.*

class MatchSummary : Fragment() {
    lateinit var args: MatchSummaryArgs

    companion object {
        fun newInstance() = MatchSummary()
    }

    private lateinit var viewModel: MatchSummaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        args = MatchSummaryArgs.fromBundle(requireArguments())
        return inflater.inflate(R.layout.match_summary_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MatchSummaryViewModel::class.java)
        // TODO: Use the ViewModel

        val data = args.matchData
        team.text = data.team1
        team3.text = data.team2
        winner.text = data.winner
        looser.text = data.looser
        mom.text = data.mom
        bom.text = data.bom
        bf.text = data.bestFielder



    }

}
