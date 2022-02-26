package io.github.cbinarycastle.betty.ui.match.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
fun MatchDetailsAppBar(
    upPress: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BettyAppBar(modifier) {
        Box(Modifier.fillMaxWidth()) {
            IconButton(onClick = upPress) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Up"
                )
            }
            Text(
                text = stringResource(R.string.app_name),
                modifier = Modifier.align(Alignment.Center),
                color = Color.Black,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = BettyTheme.typography.h6
            )
        }
    }
}

@Preview
@Composable
fun MatchDetailsAppBarPreview() {
    BettyTheme {
        MatchDetailsAppBar(upPress = {})
    }
}