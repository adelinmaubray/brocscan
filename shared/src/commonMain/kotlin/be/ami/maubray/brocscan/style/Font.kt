package be.ami.maubray.brocscan.style

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import brocscan.shared.generated.resources.BalooChettan2_Bold
import brocscan.shared.generated.resources.BalooChettan2_Regular
import brocscan.shared.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun appTypography(): Typography {
	
	val fontFamily = FontFamily(
		Font(Res.font.BalooChettan2_Regular, weight = FontWeight.Normal),
		Font(Res.font.BalooChettan2_Bold, weight = FontWeight.Bold),
	)
	
	val default = Typography()
	fun TextStyle.baloo() = copy(fontFamily = fontFamily)
	return Typography(
		displayLarge = default.displayLarge.baloo(),
		displayMedium = default.displayMedium.baloo(),
		displaySmall = default.displaySmall.baloo(),
		headlineLarge = default.headlineLarge.baloo(),
		headlineMedium = default.headlineMedium.baloo(),
		headlineSmall = default.headlineSmall.baloo(),
		titleLarge = default.titleLarge.baloo(),
		titleMedium = default.titleMedium.baloo(),
		titleSmall = default.titleSmall.baloo(),
		bodyLarge = default.bodyLarge.baloo(),
		bodyMedium = default.bodyMedium.baloo(),
		bodySmall = default.bodySmall.baloo(),
		labelLarge = default.labelLarge.baloo(),
		labelMedium = default.labelMedium.baloo(),
		labelSmall = default.labelSmall.baloo(),
		displayLargeEmphasized = default.displayLargeEmphasized.baloo(),
		displayMediumEmphasized = default.displayMediumEmphasized.baloo(),
		displaySmallEmphasized = default.displaySmallEmphasized.baloo(),
		headlineLargeEmphasized = default.headlineLargeEmphasized.baloo(),
		headlineMediumEmphasized = default.headlineMediumEmphasized.baloo(),
		headlineSmallEmphasized = default.headlineSmallEmphasized.baloo(),
		titleLargeEmphasized = default.titleLargeEmphasized.baloo(),
		titleMediumEmphasized = default.titleMediumEmphasized.baloo(),
		titleSmallEmphasized = default.titleSmallEmphasized.baloo(),
		bodyLargeEmphasized = default.bodyLargeEmphasized.baloo(),
		bodyMediumEmphasized = default.bodyMediumEmphasized.baloo(),
		bodySmallEmphasized = default.bodySmallEmphasized.baloo(),
		labelLargeEmphasized = default.labelLargeEmphasized.baloo(),
		labelMediumEmphasized = default.labelMediumEmphasized.baloo(),
		labelSmallEmphasized = default.labelSmallEmphasized.baloo(),
	)
}
