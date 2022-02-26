package io.github.cbinarycastle.betty.ui.match.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import io.github.cbinarycastle.betty.R
import io.github.cbinarycastle.betty.data.match.overall.matchOveralls
import io.github.cbinarycastle.betty.entity.MatchOverall
import org.threeten.bp.ZonedDateTime

@Composable
fun MatchOverallList(
    items: LazyPagingItems<MatchOverallModel>,
    onSelectMatch: (matchOverall: MatchOverall) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (items.loadState.refresh) {
        is LoadState.NotLoading -> {
            if (items.itemCount > 0) {
                LazyColumn(
                    modifier = modifier,
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(items) { item ->
                        if (item != null) {
                            when (item) {
                                is MatchOverallModel.Separator -> MatchOverallSeparator(item.matchAt)
                                is MatchOverallModel.Item -> {
                                    MatchOverallItem(
                                        matchOverall = item.matchOverall,
                                        onSelectMatch = onSelectMatch,
                                    )
                                }
                            }
                        }
                    }
                    if (items.loadState.append is LoadState.Loading) {
                        item {
                            Spacer(Modifier.height(8.dp))
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center,
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                    item {
                        Spacer(Modifier.height(16.dp))
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(stringResource(R.string.matches_no_result))
                }
            }
        }
        is LoadState.Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(stringResource(R.string.matches_error))
                Spacer(Modifier.height(8.dp))
                Button(onClick = { items.retry() }) {
                    Text(stringResource(R.string.retry))
                }
            }
        }
        LoadState.Loading -> MatchOverallListPlaceholder()
    }
}

@Composable
private fun MatchOverallListPlaceholder() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        MatchOverallSeparator(
            matchAt = ZonedDateTime.now(),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .placeholder(
                    visible = true,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(4.dp),
                    highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White),
                )
        )
        repeat(4) {
            MatchOverallItem(
                matchOverall = matchOveralls[0],
                onSelectMatch = {},
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .placeholder(
                        visible = true,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(4.dp),
                        highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White),
                    )
            )
        }
    }
}