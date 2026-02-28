package dev.trindadedev.myself

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource

import dev.trindadedev.myself.neo.NColors
import dev.trindadedev.myself.neo.NLayout
import dev.trindadedev.myself.theme.AppTheme

@Composable
fun App() {
  AppTheme {
    Column(
      modifier = Modifier
        .safeContentPadding()
        .fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {
      NLayout(
        colors = NColors(backgroundShadowColor = MaterialTheme.colorScheme.primary)
      ) {
        Text(text = "This is my NeoView")
      }
    }
  }
}