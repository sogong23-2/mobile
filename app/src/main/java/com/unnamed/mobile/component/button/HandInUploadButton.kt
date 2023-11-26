import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
import androidx.compose.runtime.*
import com.unnamed.mobile.api.SocketManager
import com.unnamed.mobile.api.TokenDecoder
import com.unnamed.mobile.api.TokenEncoder
import com.unnamed.mobile.component.model.MapDo
import com.unnamed.mobile.component.view.MapUiManager
import com.unnamed.mobile.ui.theme.buttonModifier
import com.unnamed.mobile.ui.theme.iconModifier

@Composable
fun HandInUploadButton(onSubmit: (MapDo) -> Unit, applicationContext: Context) {
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
                    if (
                        verifyRobot(robotLocation)
                        && verifyMap(mapSize)
                        && verifyTargets(targetPoints)
                        && verifyBlob(blobPoints)
                        && verifyHazard(hazardPoints)

                    ) {
                        val parsedMapSize = mapSize
                        val parsedRobotLocation = robotLocation
                        val parsedTargetPoints = targetPoints
                        val parsedBlobPoints = blobPoints
                        val parsedHazardPoints = hazardPoints

                        val map =
                            "ULM/$parsedMapSize$parsedRobotLocation$parsedTargetPoints$parsedBlobPoints$parsedHazardPoints"

                        onSubmit(TokenDecoder.uploadMap(map))
                        SocketManager.sendRequest(TokenEncoder.tokenMapInit())
                        showDialog = false
                    }else{
                        Toast.makeText(applicationContext, "양식에 맞춰 입력하시오", Toast.LENGTH_SHORT).show()
                    }

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

    Button(onClick = { showDialog = true }, modifier = buttonModifier) {
        Icon(
            imageVector = Icons.Default.Upload,
            contentDescription = "Upload",
            iconModifier
        )
    }
}

fun verifyMap(mapSize: String): Boolean {
    return mapSize.matches(Regex("m[0-9]+,[0-9]+/"))
}
fun verifyRobot(robotLocation: String): Boolean {
    return robotLocation.matches(Regex("r[0-9]+,[0-9]+/"))
}
fun verifyTargets(targetPoints: String): Boolean {
    return targetPoints.matches(Regex("(t[0-9]+,[0-9]+/)*"))
}
fun verifyBlob(blobPoints: String): Boolean {
    return blobPoints.matches(Regex("(b[0-9]+,[0-9]+/)*"))
}
fun verifyHazard(hazardPoints: String): Boolean {
    return hazardPoints.matches(Regex("(h[0-9]+,[0-9]+/)*"))
}
