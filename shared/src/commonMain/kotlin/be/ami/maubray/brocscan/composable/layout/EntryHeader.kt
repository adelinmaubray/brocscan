package be.ami.maubray.brocscan.composable.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
	Row(
		horizontalArrangement = Arrangement.SpaceBetween,
		verticalAlignment = Alignment.Top,
		modifier = Modifier.fillMaxWidth()
	) {
		RadioGroup(
			options = entries,
			selected = selected,
			onSelected = onSelected,
			label = { it.label }
		)
		Column(horizontalAlignment = Alignment.End) {
			Text(
				"${selected.scanned}/${selected.total}",
				color = MaterialTheme.colorScheme.secondary,
				fontWeight = FontWeight.SemiBold,
				fontSize = 40.sp
			)
		}
	}
}
