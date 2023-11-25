import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.unnamed.mobile.api.TokenDecoder
import com.unnamed.mobile.component.model.MapDo

@Composable
fun InputWindow(onSubmit: (MapDo) -> Unit) {
    var mapSize by remember { mutableStateOf("") }
    var robotLocation by remember { mutableStateOf("") }
    var targetPoints by remember { mutableStateOf("") }
    var blobPoints by remember { mutableStateOf("") }
    var hazardPoints by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            title = { Text("Input Window") },
            confirmButton = {
                Button(onClick = {
                    val parsedMapSize = mapSize
                    val parsedRobotLocation = robotLocation
                    val parsedTargetPoints = targetPoints
                    val parsedBlobPoints = blobPoints
                    val parsedHazardPoints = hazardPoints

                    val map =
                        "ULM/$parsedMapSize$parsedRobotLocation$parsedTargetPoints$parsedBlobPoints$parsedHazardPoints"

                    onSubmit(TokenDecoder.uploadMap(map))
                    showDialog = false
                }) {
                    Text("Submit")
                }
            },
            text = {
                Column {
                    // Input fields for map size, robot location, points, etc.
                    TextField(
                        value = mapSize,
                        onValueChange = { mapSize = it },
                        label = { Text("Map Size") })
                    TextField(
                        value = robotLocation,
                        onValueChange = { robotLocation = it },
                        label = { Text("Robot Location") })
                    TextField(
                        value = targetPoints,
                        onValueChange = { targetPoints = it },
                        label = { Text("Target Points") })
                    TextField(
                        value = blobPoints,
                        onValueChange = { blobPoints = it },
                        label = { Text("Blob Points") })
                    TextField(
                        value = hazardPoints,
                        onValueChange = { hazardPoints = it },
                        label = { Text("Hazard Points") })
                }
            }
        )
    }

    Button(onClick = { showDialog = true }) {
        Text("Hand In")
    }
}
