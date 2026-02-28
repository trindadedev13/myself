package dev.trindadedev.myself.neo

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class NDimens(
  val backgroundMarginTop: Dp = 6.dp,
  val backgroundMarginStart: Dp = 6.dp,
  val backgroundRadius: Dp = 3.dp,
  val foregroundStrokeWidth: Dp = 3.dp,
)

@Immutable
data class NColors(
  val backgroundShadowColor: Color = Color.Black,
  val foregroundColor: Color = Color.White,
  val foregroundStrokeColor: Color = Color.Black,
)

@Composable
fun NLayout(
  modifier: Modifier = Modifier,
  dimens: NDimens = NDimens(),
  colors: NColors = NColors(),
  animationDuration: Int = 100,
  clickable: Boolean = true,
  enabled: Boolean = true,
  onClick: () -> Unit = {},
  content: @Composable BoxScope.() -> Unit
) {
  var pressed by remember { mutableStateOf(false) }

  val offsetX by animateDpAsState(
    targetValue = if (pressed) dimens.backgroundMarginStart else 0.dp,
    animationSpec = tween(animationDuration),
    label = ""
  )

  val offsetY by animateDpAsState(
    targetValue = if (pressed) dimens.backgroundMarginTop else 0.dp,
    animationSpec = tween(animationDuration),
    label = ""
  )

  Box(modifier = modifier.wrapContentSize()) {
    Box(
      modifier = Modifier
        .matchParentSize()
        .offset(
          x = dimens.backgroundMarginStart,
          y = dimens.backgroundMarginTop
        )
        .clip(RoundedCornerShape(dimens.backgroundRadius))
        .background(
          if (enabled) colors.backgroundShadowColor
          else Color(0xFFBDBDBD)
        )
    )

    Box(
      modifier = Modifier
        .offset(x = offsetX, y = offsetY)
        .clip(RoundedCornerShape(dimens.backgroundRadius))
        .background(colors.foregroundColor)
        .border(
          dimens.foregroundStrokeWidth,
          colors.foregroundStrokeColor,
          RoundedCornerShape(dimens.backgroundRadius)
        )
        .pointerInput(clickable, enabled) {
          if (clickable && enabled) {
            detectTapGestures(
              onPress = {
                pressed = true
                val released = tryAwaitRelease()
                pressed = false

                if (released) {
                  onClick()
                }
              }
            )
          }
        }
        .padding(12.dp)
    ) {
      content()
    }
  }
}