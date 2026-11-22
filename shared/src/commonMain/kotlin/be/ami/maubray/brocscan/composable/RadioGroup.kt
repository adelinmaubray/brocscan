package be.ami.maubray.brocscan.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun <T> RadioGroup(
	options: List<T>,
	selected: T,
	onSelected: (T) -> Unit,
	label: (T) -> String,
	trailing: @Composable (T) -> Unit = {},
	modifier: Modifier = Modifier,
) {
	Column(modifier = modifier, ) {
		options.forEach { option ->
			Row(
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.spacedBy(8.dp),
				modifier = Modifier
					.fillMaxWidth()
					.clickable { onSelected(option) }
			) {
				RadioButton(
					selected = selected == option,
					onClick = { onSelected(option) },
					modifier = Modifier.scale(1.2f)
				)
				Text(
					label(option),
					color = MaterialTheme.colorScheme.secondary,
					fontSize = 20.sp
				)
				Spacer(modifier = Modifier.weight(1f))
				trailing(option)
			}
		}
	}
}
