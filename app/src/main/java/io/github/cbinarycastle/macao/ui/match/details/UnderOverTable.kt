package io.github.cbinarycastle.macao.ui.match.details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.cbinarycastle.macao.R
import io.github.cbinarycastle.macao.data.match.details.matchDetails
import io.github.cbinarycastle.macao.entity.UnderOver
import io.github.cbinarycastle.macao.ui.theme.MacaoTheme

@Composable
fun UnderOverTable(
    underOvers: List<UnderOver>,
    homeTeamName: String,
    awayTeamName: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        UnderOverHeader()
        Divider()
        UnderOverItems(
            underOvers = underOvers,
            homeTeamName = homeTeamName,
            awayTeamName = awayTeamName,
        )
    }
}

@Composable
private fun UnderOverHeader() {
    Row {
        Spacer(Modifier.width(NumberCellWidth))
        UnderOverCell(
            text = stringResource(R.string.under_over_team),
            modifier = Modifier.width(TeamCellWidth),
            horizontalArrangement = Arrangement.Start,
        )
        UnderOverCell(
            text = stringResource(R.string.under_over_under),
            modifier = Modifier.weight(1f)
        )
        UnderOverCell(
            text = stringResource(R.string.under_over_over),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun UnderOverItems(
    underOvers: List<UnderOver>,
    homeTeamName: String,
    awayTeamName: String,
    modifier: Modifier = Modifier,
) {
    underOvers.forEach {
        UnderOverItem(
            underOver = it,
            modifier = if (it.teamName == homeTeamName || it.teamName == awayTeamName) {
                modifier
                    .border(
                        width = 1.dp,
                        color = MacaoTheme.colors.primary
                    )
                    .background(color = MacaoTheme.colors.primary.copy(alpha = HighlightAlpha))
            } else {
                modifier
            }
        )
    }
}

@Composable
private fun UnderOverItem(
    underOver: UnderOver,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        UnderOverCell(
            text = underOver.number.toString(),
            modifier = Modifier.width(NumberCellWidth)
        )
        UnderOverCell(
            text = underOver.teamName,
            modifier = Modifier.width(TeamCellWidth),
            horizontalArrangement = Arrangement.Start,
            textStyle = MacaoTheme.typography.subtitle2,
        )
        UnderOverCell(
            text = "${underOver.underCount}/${underOver.matchCount} (${underOver.underPercent}%)",
            modifier = Modifier.weight(1f)
        )
        UnderOverCell(
            text = "${underOver.overCount}/${underOver.matchCount} (${underOver.overPercent}%)",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun UnderOverCell(
    text: String,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    textStyle: TextStyle = MacaoTheme.typography.body2,
) {
    Row(
        modifier = modifier.padding(vertical = 8.dp),
        horizontalArrangement = horizontalArrangement,
    ) {
        Text(
            text = text,
            style = textStyle
        )
    }
}

@Preview
@Composable
private fun UnderOverTablePreview() {
    MacaoTheme {
        UnderOverTable(
            underOvers = matchDetails.underOvers,
            homeTeamName = "Manchester City",
            awayTeamName = "Liverpool",
        )
    }
}

private const val HighlightAlpha = 0.15f
private val NumberCellWidth = 36.dp
private val TeamCellWidth = 136.dp
