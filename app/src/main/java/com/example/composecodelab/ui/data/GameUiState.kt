package com.example.composecodelab.ui.data

/**
 * @author Created by chanhypark on 1/17/24
 **/
data class GameUiState(
    val currentScrambledWord: String = "",
    val isGuessedWordWrong: Boolean = false,
    val currentWordCount:Int = 1,
    val score: Int = 0,
    val isGameOver:Boolean = false
)