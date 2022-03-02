package io.github.cbinarycastle.betty.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import io.github.cbinarycastle.betty.ui.BettyAppBar
import io.github.cbinarycastle.betty.ui.theme.BettyTheme

@Composable
fun SearchMatchesAppBar(
    title: String,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BettyAppBar(
        title = {
            Text(
                text = title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = BettyTheme.extendedTypography.appBarTitle
            )
        },
        modifier = modifier,
        navigationIcon = {
            Box(Modifier.fillMaxWidth()) {
                IconButton(onClick = onNavigateUp) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Up"
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun SearchMatchesAppBarPreview() {
    BettyTheme {
        SearchMatchesAppBar(
            title = "Manchester City",
            onNavigateUp = {}
        )
    }
}