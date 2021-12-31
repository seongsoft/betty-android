package io.github.cbinarycastle.macao.ui.match

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.cbinarycastle.macao.R
import io.github.cbinarycastle.macao.entity.OutCome
import io.github.cbinarycastle.macao.entity.TeamInfo
import io.github.cbinarycastle.macao.ui.theme.MacaoTheme
import io.github.cbinarycastle.macao.ui.theme.blueGray600
import io.github.cbinarycastle.macao.ui.theme.green800
import io.github.cbinarycastle.macao.ui.theme.red700

@Composable
fun Team(teamInfo: TeamInfo) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // TODO: Image로 교체
        Surface(
            modifier = Modifier.size(60.dp),
            color = Color.LightGray,
            shape = CircleShape
        ) {}
        Spacer(Modifier.height(4.dp))
        Text(
            text = teamInfo.teamName,
            style = MacaoTheme.typography.subtitle2
        )
        Spacer(Modifier.height(4.dp))
        Row {
            teamInfo.recentRecords.forEach {
                Spacer(Modifier.width(2.dp))
                OutComeChip(it)
                Spacer(Modifier.width(2.dp))
            }
        }
    }
}

@Composable
private fun OutComeChip(outCome: OutCome) {
    Surface(
        modifier = Modifier.size(18.dp),
        shape = CircleShape,
        color = outCome.backgroundColor(),
        contentColor = Color.White,
    ) {
        OutComeIcon(outCome)
    }
}

@Composable
private fun OutComeIcon(outCome: OutCome) = when (outCome) {
    OutCome.WIN -> Icon(
        imageVector = Icons.Default.Check,
        contentDescription = stringResource(R.string.win)
    )
    OutCome.DRAW -> Icon(
        imageVector = Icons.Default.Remove,
        contentDescription = stringResource(R.string.draw)
    )
    OutCome.LOSE -> Icon(
        imageVector = Icons.Default.Clear,
        contentDescription = stringResource(R.string.lose)
    )
}

@Composable
fun OutCome.backgroundColor() = when (this) {
    OutCome.WIN -> green800
    OutCome.DRAW -> blueGray600
    OutCome.LOSE -> red700
}