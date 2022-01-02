package io.github.cbinarycastle.macao.ui.match

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import io.github.cbinarycastle.macao.data.matchOveralls
import io.github.cbinarycastle.macao.entity.OutCome
import io.github.cbinarycastle.macao.entity.TeamInfo
import io.github.cbinarycastle.macao.ui.theme.MacaoTheme
import io.github.cbinarycastle.macao.ui.theme.neutral
import io.github.cbinarycastle.macao.ui.theme.success
import io.github.cbinarycastle.macao.ui.theme.error

@Composable
fun Team(teamInfo: TeamInfo) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        GlideImage(
            imageModel = teamInfo.logoUrl,
            modifier = Modifier.size(60.dp),
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = teamInfo.teamName,
            style = MacaoTheme.typography.subtitle2
        )
        Spacer(Modifier.height(4.dp))
        Row {
            teamInfo.recentRecords.forEach {
                Spacer(Modifier.width(2.dp))
                RecentRecordStatus(it)
                Spacer(Modifier.width(2.dp))
            }
        }
    }
}

@Composable
fun RecentRecordStatus(outCome: OutCome) {
    Surface(
        modifier = Modifier.size(12.dp),
        shape = CircleShape,
        color = outCome.backgroundColor(),
        contentColor = Color.White,
    ) {}
}

@Composable
private fun OutCome.backgroundColor() = when (this) {
    OutCome.WIN -> success
    OutCome.DRAW -> neutral
    OutCome.LOSE -> error
}

@Preview
@Composable
fun TeamPreview() {
    MacaoTheme {
        Team(teamInfo = matchOveralls[0].homeTeamInfo)
    }
}