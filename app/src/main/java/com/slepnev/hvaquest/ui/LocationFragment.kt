package com.slepnev.hvaquest.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.slepnev.hvaquest.R
import kotlinx.android.synthetic.main.fragment_location.*

/**
 * A simple [Fragment] subclass.
 */
class LocationFragment : Fragment() {

    private val args: LocationFragmentArgs by navArgs()

    private lateinit var viewModel: QuestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(activity as AppCompatActivity).get(QuestViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val question = viewModel.getQuestion(args.questionNumber)

        ivLocation.setImageResource(question.clue)

        btnNext.setOnClickListener {
            val action: NavDirections = if (args.questionNumber < viewModel.getQuestionsCount()) {
                LocationFragmentDirections.actionLocationFragmentToQuestionFragment(args.questionNumber + 1)
            } else {
                LocationFragmentDirections.actionLocationFragmentToCompletedFragment()
            }
            findNavController().navigate(action)
        }
    }


}
