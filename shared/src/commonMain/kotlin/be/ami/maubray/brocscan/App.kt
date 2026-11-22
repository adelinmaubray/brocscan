package be.ami.maubray.brocscan

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import brocscan.shared.generated.resources.*
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import kotlin.time.Clock

data class Country(val name: String, val zone: TimeZone, val flag: DrawableResource)

fun currentTimeAt(location: Country): String {
	val timeFormat = LocalTime.Format {
		hour()
		char(':')
		minute()
		char(':')
		second()
	}
	
	val time = Clock.System.now()
	val localTime = time.toLocalDateTime(location.zone).time
	return "Time in ${location.name} is ${localTime.format(timeFormat)}"
}

fun defaultCountries() = listOf(
	Country("Japan", TimeZone.of("Asia/Tokyo"), Res.drawable.jp),
	Country("France", TimeZone.of("Europe/Paris"), Res.drawable.fr),
	Country("Mexico", TimeZone.of("America/Mexico_City"), Res.drawable.mx),
	Country("Indonesia", TimeZone.of("Asia/Jakarta"), Res.drawable.id),
	Country("Egypt", TimeZone.of("Africa/Cairo"), Res.drawable.eg),
)


@Composable
@Preview
fun App(countries: List<Country> = defaultCountries()) {
	MaterialTheme {
		// region counter
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
		
		// endregion
		
		var showCountries by remember { mutableStateOf(false) }
		var timeAtLocation by remember { mutableStateOf("No location selected") }
		
		Column(
			modifier = Modifier
				.padding(20.dp)
				.safeContentPadding()
				.fillMaxSize()
		) {
			Text(
				timeAtLocation,
				style = TextStyle(fontSize = 20.sp),
				textAlign = TextAlign.Center,
				modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
			)
			Row(modifier = Modifier.padding(start = 20.dp, top = 10.dp)) {
				DropdownMenu(expanded = showCountries, onDismissRequest = { showCountries = false }) {
					countries.forEach { country ->
						DropdownMenuItem(
							text = {
								Row(verticalAlignment = Alignment.CenterVertically) {
									Image(
										painterResource(country.flag),
										contentDescription = "${country.name} flag",
										modifier = Modifier.size(35.dp).padding(end = 10.dp),
									)
									Text(country.name)
								}
							},
							onClick = {
								timeAtLocation = currentTimeAt(country)
								showCountries = false
							}
						
						)
					}
				}
			}
			
			Button(
				modifier = Modifier.padding(start = 20.dp, top = 10.dp),
				onClick = { showCountries = true })
			{
				Text("Show Time at Location!")
			}
		}
	}
}
