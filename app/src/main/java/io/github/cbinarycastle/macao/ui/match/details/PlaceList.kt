package io.github.cbinarycastle.macao.ui.match.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.cbinarycastle.macao.R
import io.github.cbinarycastle.macao.data.match.details.matchDetails
import io.github.cbinarycastle.macao.entity.Place
import io.github.cbinarycastle.macao.ui.theme.MacaoTheme

@Composable
fun PlaceList(
    totalPlace: Place,
    homePlace: Place,
    awayPlace: Place,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(vertical = 8.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        PlaceCard(
            place = totalPlace,
            title = stringResource(R.string.place_total_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        PlaceCard(
            place = homePlace,
            title = stringResource(R.string.place_home_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        PlaceCard(
            place = awayPlace,
            title = stringResource(R.string.place_away_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
private fun PlaceCard(
    place: Place,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(modifier) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MacaoTheme.typography.h6
            )
            Spacer(Modifier.height(16.dp))
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                PlaceRow(
                    label = "경기",
                    homeTeamValue = place.home.totalMatchCount,
                    awayTeamValue = place.away.totalMatchCount
                )
                PlaceRow(
                    label = "승",
                    homeTeamValue = place.home.winMatchCount,
                    awayTeamValue = place.away.winMatchCount,
                    comparator = Comparator.naturalOrder()
                )
                PlaceRow(
                    label = "무",
                    homeTeamValue = place.home.drawMatchCount,
                    awayTeamValue = place.away.drawMatchCount
                )
                PlaceRow(
                    label = "패",
                    homeTeamValue = place.home.loseMatchCount,
                    awayTeamValue = place.away.loseMatchCount,
                    comparator = Comparator.reverseOrder()
                )
                PlaceRow(
                    label = "득점",
                    homeTeamValue = place.home.goalFor,
                    awayTeamValue = place.away.goalFor,
                    comparator = Comparator.naturalOrder()
                )
                PlaceRow(
                    label = "실점",
                    homeTeamValue = place.home.goalAgainst,
                    awayTeamValue = place.away.goalAgainst,
                    comparator = Comparator.reverseOrder()
                )
                PlaceRow(
                    label = "승점",
                    homeTeamValue = place.home.points,
                    awayTeamValue = place.away.points,
                    comparator = Comparator.naturalOrder()
                )
            }
        }
    }
}

@Composable
private fun PlaceRow(
    label: String,
    homeTeamValue: Int,
    awayTeamValue: Int,
    modifier: Modifier = Modifier,
    comparator: Comparator<Int> = Comparator { _, _ -> 0 },
) {
    Row(modifier = modifier) {
        Text(
            text = homeTeamValue.toString(),
            modifier = Modifier.weight(1f),
            color = if (comparator.compare(homeTeamValue, awayTeamValue) > 0) {
                MacaoTheme.extendedColors.win
            } else {
                MacaoTheme.colors.onSurface
            },
            textAlign = TextAlign.End,
            style = MacaoTheme.typography.subtitle2
        )
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = label,
                modifier = Modifier.width(84.dp),
                textAlign = TextAlign.Center,
                style = MacaoTheme.typography.body2
            )
        }
        Text(
            text = awayTeamValue.toString(),
            modifier = Modifier.weight(1f),
            color = if (comparator.compare(awayTeamValue, homeTeamValue) > 0) {
                MacaoTheme.extendedColors.win
            } else {
                MacaoTheme.colors.onSurface
            },
            style = MacaoTheme.typography.subtitle2
        )
    }
}

@Preview
@Composable
private fun PlaceListPreview() {
    MacaoTheme {
        PlaceList(
            totalPlace = matchDetails.totalPlace,
            homePlace = matchDetails.homePlace,
            awayPlace = matchDetails.awayPlace
        )
    }
}