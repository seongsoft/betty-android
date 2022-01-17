package io.github.cbinarycastle.macao.ui.match.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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
    Row(modifier.verticalScroll(rememberScrollState())) {
        Column(Modifier.width(IntrinsicSize.Max)) {
            FlexibleUnderOverHeader()
            FlexibleUnderOverItems(
                underOvers = underOvers,
                homeTeamName = homeTeamName,
                awayTeamName = awayTeamName,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column {
            UnderOverHeader()
            UnderOverItems(
                underOvers = underOvers,
                homeTeamName = homeTeamName,
                awayTeamName = awayTeamName,
            )
        }
    }
}

@Composable
private fun FlexibleUnderOverHeader() {
    Row {
        Spacer(Modifier.width(UnderOverCellDefaults.UnderOverNumberWidth))
        UnderOverCell(
            text = stringResource(R.string.under_over_team),
            horizontalArrangement = Arrangement.Start,
        )
    }
}

@Composable
private fun FlexibleUnderOverItems(
    underOvers: List<UnderOver>,
    homeTeamName: String,
    awayTeamName: String,
    modifier: Modifier = Modifier,
) {
    underOvers.forEach {
        FlexibleUnderOverItem(
            underOver = it,
            modifier = modifier.background(
                color = if (it.teamName == homeTeamName || it.teamName == awayTeamName) {
                    MacaoTheme.colors.primary.copy(alpha = UnderOverCellDefaults.HighlightAlpha)
                } else {
                    Color.Unspecified
                }
            )
        )
    }
}

@Composable
private fun FlexibleUnderOverItem(
    underOver: UnderOver,
    modifier: Modifier = Modifier,
) {
    Row(modifier) {
        UnderOverCell(
            text = underOver.number.toString(),
            modifier = Modifier.width(UnderOverCellDefaults.UnderOverNumberWidth)
        )
        UnderOverCell(
            text = underOver.teamName,
            modifier = Modifier.padding(end = 8.dp),
            horizontalArrangement = Arrangement.Start,
            textStyle = MacaoTheme.typography.subtitle2,
        )
    }
}

@Composable
private fun UnderOverHeader() {
    Row {
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
) {
    underOvers.forEach {
        UnderOverItem(
            underOver = it,
            modifier = Modifier.background(
                color = if (it.teamName == homeTeamName || it.teamName == awayTeamName) {
                    MacaoTheme.colors.primary.copy(alpha = UnderOverCellDefaults.HighlightAlpha)
                } else {
                    Color.Unspecified
                }
            )
        )
    }
}

@Composable
private fun UnderOverItem(
    underOver: UnderOver,
    modifier: Modifier = Modifier,
) {
    Row(modifier) {
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

private object UnderOverCellDefaults {
    const val HighlightAlpha = 0.15f
    val UnderOverNumberWidth = 36.dp
}