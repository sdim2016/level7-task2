package com.slepnev.hvaquest.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.slepnev.hvaquest.R
import kotlinx.android.synthetic.main.fragment_question.*

/**
 * A simple [Fragment] subclass.
 */
class QuestionFragment : Fragment() {

    private val args: QuestionFragmentArgs by navArgs()

    private lateinit var viewModel: QuestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(activity as AppCompatActivity).get(QuestViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val question = viewModel.getQuestion(args.questionNumber)

        tvQuestion.text = question.question

        tvQuestionNumber.text = "${args.questionNumber}/${viewModel.getQuestionsCount()}"

        question.choices.forEach {
            val radioButton = RadioButton(context)
            radioButton.id = View.generateViewId()
            radioButton.text = it
            rgOptions.addView(radioButton)
        }

        btnConfirm.setOnClickListener {

            if (viewModel.isAnswerCorrect(
                    rgOptions.findViewById<RadioButton>(rgOptions.checkedRadioButtonId).text.toString(),
                    question)) {
                val action = QuestionFragmentDirections.actionQuestionFragmentToLocationFragment(args.questionNumber)
                findNavController().navigate(action)
            } else {
                Toast.makeText(context, "Wrong", Toast.LENGTH_SHORT).show()

            }
        }

    }

}
