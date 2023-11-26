import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
import androidx.compose.runtime.*
import com.unnamed.mobile.api.SocketManager
import com.unnamed.mobile.api.TokenDecoder
import com.unnamed.mobile.component.model.MapDo
import com.unnamed.mobile.ui.theme.buttonModifier
import com.unnamed.mobile.ui.theme.iconModifier
import kotlinx.coroutines.runBlocking

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
                        UserInputHandler.verifyRobot(robotLocation)
                        && UserInputHandler.verifyMap(mapSize)
                        && UserInputHandler.verifyTargets(targetPoints)
                        && UserInputHandler.verifyBlob(blobPoints)
                        && UserInputHandler.verifyHazard(hazardPoints)

                    ) {
                        val parsedMapSize = mapSize
                        val parsedRobotLocation = robotLocation
                        val parsedTargetPoints = targetPoints
                        val parsedBlobPoints = blobPoints
                        val parsedHazardPoints = hazardPoints

                        val map =
                            "ULM/$parsedMapSize$parsedRobotLocation$parsedTargetPoints$parsedBlobPoints$parsedHazardPoints"

                        onSubmit(UserInputHandler.stringToMapDo(map))
                        runBlocking {
                            val response = SocketManager.initRequest()
                        }
                        showDialog = false
                    } else {
                        Toast.makeText(applicationContext, "양식에 맞춰 입력하시오", Toast.LENGTH_SHORT)
                            .show()
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

object UserInputHandler{
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

    private fun parseToToken(data: String): List<String> {
        val tokens = mutableListOf<String>()
        var currentIndex = 4

        while (currentIndex < data.length) {
            val nextIndex = data.indexOf('/', currentIndex)
            if (nextIndex != -1) {
                val substring = data.substring(currentIndex, nextIndex + 1)
                tokens.add(substring)
                currentIndex = nextIndex + 1
            } else {
                break
            }
        }

        return tokens
    }

    fun stringToMapDo(stream: String): MapDo {
        var mapSize: Pair<Int, Int> = Pair(0, 0)
        var robot: Pair<Int, Int> = Pair(0, 0)
        var blobs: MutableList<Pair<Int, Int>> = mutableListOf()
        var hazards: MutableList<Pair<Int, Int>> = mutableListOf()
        var targetPoints: MutableList<Pair<Int, Int>> = mutableListOf()

        for (token in parseToToken(stream)) {

            val type = token[0]
            val payload = token.substring(1, token.length - 1)
            val coordination = payload.split(",")

            when (type) {
                'm' -> {
                    mapSize = Pair(coordination[0].toInt(), coordination[1].toInt())
                }
                'r' -> {
                    robot = Pair(coordination[0].toInt(), coordination[1].toInt())
                }
                'b' -> {
                    blobs.add(Pair(coordination[0].toInt(), coordination[1].toInt()))
                }
                'h' -> {
                    hazards.add(Pair(coordination[0].toInt(), coordination[1].toInt()))
                }
                't' -> {
                    targetPoints.add(Pair(coordination[0].toInt(), coordination[1].toInt()))
                }
            }
        }

        return MapDo(mapSize, robot, blobs, hazards, targetPoints)
    }
}

