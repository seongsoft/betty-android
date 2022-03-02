package io.github.cbinarycastle.betty.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import io.github.cbinarycastle.betty.R
import io.github.cbinarycastle.betty.domain.Result
import io.github.cbinarycastle.betty.ui.BettyAppBar

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onNavigateUp: () -> Unit,
    onSearch: (String) -> Unit,
) {
    val keyword by viewModel.keyword.collectAsState()
    val matchedTeamsResult by viewModel.matchedTeams.collectAsState(Result.Success(emptyList()))

    SearchScreen(
        keyword = keyword,
        matchedTeamsResult = matchedTeamsResult,
        onNavigateUp = onNavigateUp,
        onInputChange = { viewModel.updateKeyword(it) },
        onClearInput = { viewModel.clearKeyword() },
        onSearch = onSearch,
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SearchScreen(
    keyword: String,
    matchedTeamsResult: Result<List<String>>,
    onNavigateUp: () -> Unit,
    onInputChange: (String) -> Unit,
    onClearInput: () -> Unit,
    onSearch: (String) -> Unit,
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
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            if (keyword.isNotBlank()) {
                                onSearch(keyword)
                            }
                        }
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

        Box(Modifier.fillMaxSize()) {
            when (matchedTeamsResult) {
                is Result.Success -> {
                    val items = matchedTeamsResult.data
                    if (items.isEmpty()) {
                        Text(
                            text = stringResource(R.string.search_no_result),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    } else {
                        LazyColumn {
                            items(items) { teamName ->
                                SearchResultListItem(
                                    teamName = teamName,
                                    onClick = onSearch,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                }
                is Result.Error -> {
                    Text(
                        text = stringResource(R.string.search_error),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Result.Loading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
        }
    }
}