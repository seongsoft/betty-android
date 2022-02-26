package io.github.cbinarycastle.betty.ui.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.Velocity
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

    val progress: Float
        @FloatRange(from = 0.0, to = 1.0)
        get() = height.toFloat() / maxHeight.toFloat()

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

    suspend fun fling(flingBehavior: FlingBehavior, velocity: Float): Float {
        var left = velocity
        scroll {
            with(flingBehavior) {
                left = performFling(velocity)
            }
        }
        return left
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
    val state: CollapsibleState,
    val flingBehavior: FlingBehavior,
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

    override suspend fun onPreFling(available: Velocity): Velocity {
        val velocity = available.y
        if (velocity >= 0) {
            return Velocity.Zero
        }

        val left = state.fling(flingBehavior = flingBehavior, velocity = available.y)
        return Velocity(0f, velocity - left)
    }

    override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
        val velocity = available.y
        if (velocity <= 0) {
            return Velocity.Zero
        }

        val left = state.fling(flingBehavior = flingBehavior, velocity = available.y)
        return Velocity(0f, velocity - left)
    }
}

@Composable
fun CollapsibleLayout(
    state: CollapsibleState,
    collapsible: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    val flingBehavior = ScrollableDefaults.flingBehavior()
    val nestedScrollConnection = remember {
        CollapsibleNestedScrollConnection(state, flingBehavior)
    }

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