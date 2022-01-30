package io.github.cbinarycastle.macao.ui.match.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
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
        modifier = modifier.padding(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        PlaceItem(
            place = totalPlace,
            title = stringResource(R.string.place_total_title),
        )
        PlaceItem(
            place = homePlace,
            title = stringResource(R.string.place_home_title),
        )
        PlaceItem(
            place = awayPlace,
            title = stringResource(R.string.place_away_title),
        )
    }
}

@Composable
private fun PlaceItem(
    place: Place,
    title: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Text(
            text = title,
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MacaoTheme.typography.h6
        )
        Spacer(Modifier.height(8.dp))
        PlaceSurface(
            place = place,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
private fun PlaceSurface(
    place: Place,
    modifier: Modifier = Modifier
) {
    Card(modifier) {
        Column(
            modifier = modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
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
    Column(modifier) {
        Box(Modifier.fillMaxWidth()) {
            Text(
                text = homeTeamValue.toString(),
                modifier = Modifier.align(Alignment.CenterStart),
                color = if (comparator.compare(homeTeamValue, awayTeamValue) > 0) {
                    MacaoTheme.extendedColors.win
                } else {
                    MacaoTheme.colors.onSurface
                },
                style = MacaoTheme.typography.subtitle2
            )
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = label,
                    modifier = Modifier.align(Alignment.TopCenter),
                    textAlign = TextAlign.Center,
                    style = MacaoTheme.typography.body2
                )
            }
            Text(
                text = awayTeamValue.toString(),
                modifier = Modifier.align(Alignment.CenterEnd),
                color = if (comparator.compare(awayTeamValue, homeTeamValue) > 0) {
                    MacaoTheme.extendedColors.win
                } else {
                    MacaoTheme.colors.onSurface
                },
                textAlign = TextAlign.End,
                style = MacaoTheme.typography.subtitle2
            )
        }
        Spacer(Modifier.height(4.dp))
        Row {
            Surface(
                modifier = Modifier
                    .height(4.dp)
                    .then(
                        if (homeTeamValue == 0) {
                            Modifier.width(4.dp)
                        } else {
                            Modifier.weight(homeTeamValue.toFloat())
                        }
                    ),
                color = MacaoTheme.colors.primary,
                shape = CircleShape,
                content = {}
            )
            Spacer(Modifier.width(4.dp))
            Surface(
                modifier = Modifier
                    .height(4.dp)
                    .then(
                        if (awayTeamValue == 0) {
                            Modifier.width(4.dp)
                        } else {
                            Modifier.weight(awayTeamValue.toFloat())
                        }
                    ),
                color = MacaoTheme.colors.secondary,
                shape = CircleShape,
                content = {}
            )
        }
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