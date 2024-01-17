package com.example.composecodelab.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.composecodelab.ui.data.GameUiState
import com.example.composecodelab.ui.data.MAX_NO_OF_WORDS
import com.example.composecodelab.ui.data.SCORE_INCREASE
import com.example.composecodelab.ui.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * @author Created by chanhypark on 1/17/24
 **/
class GameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    var userGuess by mutableStateOf("")
        private set

    private lateinit var currentWord: String
    private var usedWords: MutableSet<String> = mutableSetOf()

    init {
        resetGame()
    }

    fun skipWord() {
        updateGameState(_uiState.value.score)
        updateUserGuess("")
    }

    fun checkUserGuess() {
        if (userGuess.equals(currentWord, ignoreCase = true)) {
            val score = _uiState.value.score.plus(SCORE_INCREASE)
            updateGameState(score)
        } else {
            _uiState.update {
                it.copy(isGuessedWordWrong = true)
            }
        }
        updateUserGuess("")
    }

    private fun updateGameState(updateScore: Int) {
        if (usedWords.size == MAX_NO_OF_WORDS) {
            _uiState.update {
                it.copy(
                    isGameOver = true,
                    score = updateScore,
                    isGuessedWordWrong = false
                )
            }
        } else {
            _uiState.update {
                it.copy(
                    currentScrambledWord = pickRandomWordAndShuffle(),
                    isGuessedWordWrong = false,
                    score = updateScore,
                    currentWordCount = it.currentWordCount.inc(),
                    isGameOver = false
                )
            }
        }
    }

    fun updateUserGuess(guessWord: String) {
        userGuess = guessWord
    }

    private fun pickRandomWordAndShuffle(): String {
        currentWord = allWords.random()
        return if (usedWords.contains(currentWord)) {
            pickRandomWordAndShuffle()
        } else {
            usedWords.add(currentWord)
            shuffleCurrentWord(currentWord)
        }
    }

    private fun shuffleCurrentWord(word: String): String {
        val tempWord = word.toCharArray()
        tempWord.shuffle()
        while (String(tempWord) == word) {
            tempWord.shuffle()
        }
        return String(tempWord)
    }

    fun resetGame() {
        usedWords.clear()
        _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
    }
}