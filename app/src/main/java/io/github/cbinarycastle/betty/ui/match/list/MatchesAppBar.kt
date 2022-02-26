package io.github.cbinarycastle.betty.ui.match.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import io.github.cbinarycastle.betty.R
import io.github.cbinarycastle.betty.ui.BettyAppBar
import io.github.cbinarycastle.betty.ui.theme.BettyTheme

@Composable
fun MatchesAppBar(
    title: String?,
    onSearchButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BettyAppBar(modifier) {
        Box(Modifier.fillMaxWidth()) {
            Text(
                text = title ?: stringResource(R.string.app_name),
                modifier = Modifier.align(Alignment.Center),
                color = Color.Black,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = BettyTheme.typography.h6
            )
            IconButton(
                onClick = onSearchButtonClick,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            }
        }
    }
}

@Preview
@Composable
fun MatchesAppBarPreview() {
    BettyTheme {
        MatchesAppBar(
            title = stringResource(R.string.app_name),
            onSearchButtonClick = {}
        )
    }
}