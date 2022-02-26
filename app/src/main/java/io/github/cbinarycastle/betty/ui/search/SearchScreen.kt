package io.github.cbinarycastle.betty.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import io.github.cbinarycastle.betty.domain.Result
import io.github.cbinarycastle.betty.ui.BettyAppBar

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onNavigateUp: () -> Unit,
) {
    val keyword by viewModel.keyword.collectAsState()
    val matchedTeamsResult by viewModel.matchedTeams.collectAsState(Result.Success(emptyList()))

    SearchScreen(
        keyword = keyword,
        matchedTeamsResult = matchedTeamsResult,
        onInputChange = { viewModel.updateKeyword(it) },
        onClearInput = { viewModel.clearKeyword() },
        onNavigateUp = onNavigateUp,
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SearchScreen(
    keyword: String,
    matchedTeamsResult: Result<List<String>>,
    onInputChange: (String) -> Unit,
    onClearInput: () -> Unit,
    onNavigateUp: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Column {
        BettyAppBar(
            navigationIcon = {
                IconButton(onClick = onNavigateUp) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Up",
                    )
                }
            },
            title = {
                TextField(
                    value = keyword,
                    onValueChange = onInputChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    trailingIcon = {
                        IconButton(onClick = onClearInput) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear"
                            )
                        }
                    },
                    singleLine = true,
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                )
            }
        )

        when (matchedTeamsResult) {
            is Result.Success -> {
                val items = matchedTeamsResult.data
                LazyColumn {
                    items(items) {
                        Text(
                            text = it,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
            is Result.Error -> Text("error")
            Result.Loading -> {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
        }
    }
}