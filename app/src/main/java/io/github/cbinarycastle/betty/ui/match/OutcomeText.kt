package io.github.cbinarycastle.betty.ui.match

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.cbinarycastle.betty.R
import io.github.cbinarycastle.betty.entity.Outcome

@Composable
fun Outcome.text() = when (this) {
    Outcome.WIN -> stringResource(R.string.outcome_win)
    Outcome.DRAW -> stringResource(R.string.outcome_draw)
    Outcome.LOSE -> stringResource(R.string.outcome_lose)
}