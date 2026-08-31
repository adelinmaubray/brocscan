package be.ami.maubray.brocscan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

@Composable
@Preview
fun App() {
    MaterialTheme {
//        var showContent by remember { mutableStateOf(false) }
//        Column(
//            modifier = Modifier
//                .background(MaterialTheme.colorScheme.primaryContainer)
//                .safeContentPadding()
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//        ) {
//			Text(
//				text = "Today's date is ${today()}",
//				modifier = Modifier.padding(20.dp),
//				fontSize = 16.sp,
//				textAlign = TextAlign.Center,
//			)
//            Button(onClick = { showContent = !showContent }) {
//                Text("Click me!")
//            }
//            AnimatedVisibility(showContent) {
//                val greeting = remember { Greeting().greet() }
//                Column(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                ) {
//                    Image(painterResource(Res.drawable.compose_multiplatform), null)
//                    Text("Compose: $greeting")
//                }
//            }
//        }
		
		var timeAtLocation by remember { mutableStateOf("No location selected") }

		Column (
			modifier = Modifier
				.safeContentPadding()
				.fillMaxSize()
		) {
			Text(timeAtLocation)
			Button(onClick = { timeAtLocation = "13:30"}) {
				Text("Show Time at Location!")
			}
		}
    }
}

fun today(): String {
	val now = Clock.System.now()
	val zone = TimeZone.currentSystemDefault()
	return now.toLocalDateTime(zone).toString().substringBefore('T')
}
