package io.github.cbinarycastle.macao.ui.match

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import io.github.cbinarycastle.macao.entity.OutCome
import io.github.cbinarycastle.macao.entity.TeamInfo
import io.github.cbinarycastle.macao.ui.theme.MacaoTheme
import io.github.cbinarycastle.macao.ui.theme.blueGray600
import io.github.cbinarycastle.macao.ui.theme.green800
import io.github.cbinarycastle.macao.ui.theme.red700

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
private fun RecentRecordStatus(outCome: OutCome) {
    Surface(
        modifier = Modifier.size(12.dp),
        shape = CircleShape,
        color = outCome.backgroundColor(),
        contentColor = Color.White,
    ) {}
}

@Composable
fun OutCome.backgroundColor() = when (this) {
    OutCome.WIN -> green800
    OutCome.DRAW -> blueGray600
    OutCome.LOSE -> red700
}