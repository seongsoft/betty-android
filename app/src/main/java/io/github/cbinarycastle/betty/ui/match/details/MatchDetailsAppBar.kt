package io.github.cbinarycastle.betty.ui.match.details

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.cbinarycastle.betty.ui.BettyAppBar
import io.github.cbinarycastle.betty.ui.theme.BettyTheme
import timber.log.Timber

@Composable
fun MatchDetailsAppBar(
    title: String?,
    titleAlpha: Float,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BettyAppBar(
        modifier = modifier,
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
    ) {
        IconButton(onClick = onNavigateUp) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Up"
            )
        }
        if (title != null) {
            Text(
                text = title,
                modifier = Modifier.alpha(titleAlpha),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = BettyTheme.extendedTypography.appBarTitle
            )
        }
    }
}

@Preview
@Composable
fun MatchDetailsAppBarPreview() {
    BettyTheme {
        MatchDetailsAppBar(
            title = null,
            titleAlpha = 0f,
            onNavigateUp = {},
        )
    }
}