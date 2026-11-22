package be.ami.maubray.brocscan.style

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val BROC_PRIMARY = Color(0xFFE9BA1A)
val BROC_PRIMARY_LIGHT = Color(0xFFF6D050)
val BROC_PRIMARY_DARK = Color(0xFF000000)
val BROC_DARK = Color(0xFF885C2F)
val BROC_ACCENT = Color(0xFFFCF5DE)
val BROC_RED = Color(0xFFD1002A)
val BROC_GREEN = Color(0xFF109418)

val BrocColorScheme: ColorScheme = lightColorScheme(
	primary = BROC_PRIMARY,
	onPrimary = BROC_PRIMARY_DARK,
	primaryContainer = BROC_PRIMARY_LIGHT,
	onPrimaryContainer = BROC_DARK,
	secondary = BROC_PRIMARY_DARK,
	onSecondary = Color.White,
	tertiary = BROC_ACCENT,
	error = BROC_RED,
	background = BROC_ACCENT,
	surface = Color.White,
)
