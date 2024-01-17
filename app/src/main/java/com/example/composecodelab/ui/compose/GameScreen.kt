package com.example.composecodelab.ui.compose

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composecodelab.R
import com.example.composecodelab.ui.GameViewModel
import com.example.composecodelab.ui.theme.ComposeCodelabTheme

/**
 * @author Created by chanhypark on 1/17/24
 **/


@Composable
fun GameScreen(
    gameViewModel: GameViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val gameUiState = gameViewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .imePadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(20.dp)
        )
        GameLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            currentScrambledWord = gameUiState.value.currentScrambledWord,
            onUserGuessChanged = {
                gameViewModel.updateUserGuess(it)
            },
            onKeyboardDone = {
                gameViewModel.checkUserGuess()
            },
            userGuess = gameViewModel.userGuess,
            isGuessWrong = gameUiState.value.isGuessedWordWrong,
            wordCount = gameUiState.value.currentWordCount
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    gameViewModel.checkUserGuess()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.submit),
                    fontSize = 16.sp
                )
            }
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    gameViewModel.skipWord()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.skip),
                    fontSize = 16.sp
                )
            }
            GameStatus(
                modifier = modifier,
                score = gameUiState.value.score
            )
        }

        if (gameUiState.value.isGameOver) {
            FinalScoreDialog(
                score = gameUiState.value.score,
                onPlayAgain = { gameViewModel.resetGame() })
        }

    }
}

@Composable
fun GameStatus(score: Int = 0, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.score, score),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(8.dp)
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameLayout(
    modifier: Modifier = Modifier,
    currentScrambledWord: String,
    onUserGuessChanged: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    userGuess: String,
    isGuessWrong: Boolean,
    wordCount: Int
) {
    val mediumPadding = 16.dp
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(mediumPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(mediumPadding)
        ) {
            Text(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.surfaceTint)
                    .padding(horizontal = 10.dp, vertical = 4.dp)
                    .align(alignment = Alignment.End),
                text = stringResource(R.string.word_count, wordCount),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = currentScrambledWord,
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                text = stringResource(R.string.instructions),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )
            OutlinedTextField(
                value = userGuess,
                singleLine = true,
                shape = MaterialTheme.shapes.large,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                onValueChange = onUserGuessChanged,
                label = {
                    if (!isGuessWrong) Text(stringResource(R.string.enter_your_word))
                    else Text(stringResource(id = R.string.wrong_guess))
                },
                isError = isGuessWrong,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onKeyboardDone.invoke()
                    }
                )
            )
        }
    }
}

@Composable
fun FinalScoreDialog(
    score: Int,
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activity = (LocalContext.current as Activity)
    AlertDialog(
        onDismissRequest = {

        },
        dismissButton = {
            TextButton(onClick = { activity.finish() }) {
                Text(text = stringResource(id = R.string.exit))
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onPlayAgain.invoke()
            }) {
                Text(text = stringResource(id = R.string.play_again))
            }
        },
        modifier = modifier,
        title = { Text(stringResource(R.string.congratulations)) },
        text = { Text(stringResource(R.string.you_scored, score)) }

    )
}

@Preview(showBackground = true)
@Composable
fun LayoutPreview() {
    ComposeCodelabTheme {
        GameScreen()
//        GameStatus(score = 0)
//        GameLayout()
//        FinalScoreDialog(onPlayAgain = {},0)
    }
}