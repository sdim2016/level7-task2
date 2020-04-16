package com.slepnev.hvaquest.ui

import androidx.lifecycle.ViewModel
import com.slepnev.hvaquest.data.QuestRepository
import com.slepnev.hvaquest.model.Question

class QuestViewModel : ViewModel() {
    private val questRepository = QuestRepository()

    private val questions = questRepository.getHvaQuest()

    fun getQuestion(number: Int):Question {
        return questions[number-1]
    }

    fun isAnswerCorrect(answer: String, question: Question):Boolean {
        return answer == question.correctAnswer
    }
}