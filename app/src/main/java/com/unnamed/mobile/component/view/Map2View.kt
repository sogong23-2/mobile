import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Map2View(mapSize: Pair<Int, Int>){
    val size: Pair<Int, Int> = mapSize

    Column {
        for (row in 0 until size.first) {
            Row {
                for (col in 0 until size.second) {
                    ChessSquare()
                }
            }
        }
    }

}

@Composable
fun ChessSquare() {
    Box(modifier = Modifier.size(60.dp).border(1.dp, Color.Black))
}
