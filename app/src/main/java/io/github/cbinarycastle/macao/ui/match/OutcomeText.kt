package io.github.cbinarycastle.macao.ui.match

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.cbinarycastle.macao.R
import io.github.cbinarycastle.macao.entity.Outcome

@Composable
fun Outcome.text() = when (this) {
    Outcome.WIN -> stringResource(R.string.win)
    Outcome.DRAW -> stringResource(R.string.draw)
    Outcome.LOSE -> stringResource(R.string.lose)
}