package be.ami.maubray.brocscan.style

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val PRIMARY = Color(0xFFE9BA1A)
val PRIMARY_LIGHT = Color(0xFFF6D050)
val DARK = Color(0xFF000000)
val SECONDARY = Color(0xFF885C2F)
val BACKGROUND = Color(0xFFFCF5DE)
val RED = Color(0xFFD1002A)
val GREEN = Color(0xFF109418)
val TURQUOISE = Color(0xFF17A2B8)

val appColorScheme: ColorScheme = lightColorScheme(
	primary = PRIMARY,
	inversePrimary = BACKGROUND,
	onPrimary = DARK,
	primaryContainer = PRIMARY_LIGHT,
	onPrimaryContainer = DARK,
	secondary = SECONDARY,
	onSecondary = Color.White,
	tertiary = TURQUOISE,
	error = RED,
	background = BACKGROUND,
	surface = Color.White,
)
