package io.github.cbinarycastle.macao.ui.components

import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.IntOffset
import kotlin.math.max
import kotlin.math.roundToInt

@Stable
class CollapsibleState(initialHeight: Int) : ScrollableState {

    var height: Int by mutableStateOf(initialHeight)
        private set

    var maxHeight: Int = 0
        internal set(value) {
            field = max(field, value)
        }

    private var accumulator: Float = 0f

    private val scrollableState = ScrollableState {
        val absolute = height + it + accumulator
        val newValue = absolute.coerceIn(0f, maxHeight.toFloat())
        val changed = absolute != newValue
        val consumed = newValue - height
        val consumedInt = consumed.roundToInt()
        height += consumedInt
        accumulator = consumed - consumedInt

        // Avoid floating-point rounding error
        if (changed) consumed else it
    }

    override val isScrollInProgress: Boolean
        get() = scrollableState.isScrollInProgress

    override fun dispatchRawDelta(delta: Float): Float = scrollableState.dispatchRawDelta(delta)

    override suspend fun scroll(
        scrollPriority: MutatePriority,
        block: suspend ScrollScope.() -> Unit
    ) = scrollableState.scroll(scrollPriority, block)
}

@Composable
fun rememberCollapsibleState(): CollapsibleState = remember {
    CollapsibleState(Int.MAX_VALUE)
}

class CollapsibleNestedScrollConnection(
    val state: CollapsibleState
) : NestedScrollConnection {

    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        val delta = available.y
        if (delta >= 0) {
            return Offset.Zero
        }

        val consumed = state.dispatchRawDelta(delta)
        return Offset(0f, consumed)
    }

    override fun onPostScroll(
        consumed: Offset,
        available: Offset,
        source: NestedScrollSource
    ): Offset {
        val delta = available.y
        if (delta <= 0) {
            return Offset.Zero
        }

        val weConsumed = state.dispatchRawDelta(delta)
        return Offset(0f, weConsumed)
    }
}

@Composable
fun CollapsibleLayout(
    state: CollapsibleState,
    collapsible: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    val nestedScrollConnection = remember { CollapsibleNestedScrollConnection(state) }

    Layout(
        content = {
            collapsible()
            content()
        },
        modifier = Modifier.nestedScroll(nestedScrollConnection)
    ) { measurables, constraints ->
        val collapsiblePlaceable = measurables[0].measure(
            constraints.copy(maxHeight = state.height)
        )
        val contentPlaceable = measurables[1].measure(
            constraints.copy(maxHeight = constraints.maxHeight - collapsiblePlaceable.height)
        )

        state.maxHeight = collapsiblePlaceable.height

        layout(constraints.maxWidth, constraints.maxHeight) {
            collapsiblePlaceable.place(IntOffset.Zero)
            contentPlaceable.place(x = 0, y = collapsiblePlaceable.height)
        }
    }
}