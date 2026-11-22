package be.ami.maubray.brocscan.composable.layout

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import be.ami.maubray.brocscan.composable.RadioGroup
import be.ami.maubray.brocscan.model.Entry

@Composable
fun EntryHeader(
	entries: List<Entry>,
	selected: Entry,
	onSelected: (Entry) -> Unit,
) {
	RadioGroup(
		options = entries,
		selected = selected,
		onSelected = onSelected,
		label = { entry -> entry.label },
		trailing = { entry ->
			Text(
				"${entry.scanned}/${entry.total}",
				color = MaterialTheme.colorScheme.secondary,
				fontWeight = FontWeight.SemiBold,
				fontSize = 20.sp
			)
		},
		modifier = Modifier.fillMaxWidth()
	)
}
