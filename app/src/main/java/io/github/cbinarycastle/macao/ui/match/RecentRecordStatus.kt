package io.github.cbinarycastle.macao.ui.match

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.cbinarycastle.macao.entity.OutCome
import io.github.cbinarycastle.macao.ui.theme.MacaoTheme

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
    OutCome.WIN -> MacaoTheme.extendedColors.success
    OutCome.DRAW -> MacaoTheme.extendedColors.neutral
    OutCome.LOSE -> MacaoTheme.colors.error
}