package com.unnamed.mobile.component.view
import com.unnamed.mobile.component.model.*
import com.unnamed.mobile.component.viewmodel.ComponentViewModel

object MapUiManager {
    //TODO 쓰이는 properties는 다 외부에서 정의할 수 있도록
    val mapSize: Pair<Int, Int> = Pair(7, 6)
    var robotStatus: String = "idle"
    val componentViewModel = ComponentViewModel()

    //TODO 어딘가로부터 Component를 받아와야 함
    private val components: MutableList<Component> = mutableListOf(
        Robot(Pair(6.0F, 5.0F)),
        Blob(Pair(0, 0)),
        Hazard(Pair(1, 1)),
        Blob(Pair(2, 2)),
        TargetPoint(Pair(4, 1)),
    )

    //TODO make change
    fun updateMap(){
    }
    fun uploadMap(){
        print("uploadMap")
        componentViewModel.initComponent(components)
    }
    fun clearMap(){}
    fun updateRobotStatus(status: String){
        componentViewModel.clearComponents()
        robotStatus = status
    }

}

