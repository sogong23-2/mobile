package com.unnamed.mobile.component.view
import com.unnamed.mobile.component.model.*
import com.unnamed.mobile.component.viewmodel.ComponentViewModel

object MapUiManager {
    //TODO 쓰이는 properties는 다 외부에서 정의할 수 있도록
    val mapSize: Pair<Int, Int> = Pair(5, 6)
    var robotStatus: String = "idle"
    val componentViewModel = ComponentViewModel()

    //TODO 어딘가로부터 Component를 받아와야 함
    private val components: MutableList<Component> = mutableListOf(
        Blob(Pair(0, 0)),
        Hazard(Pair(1, 1)),
        Blob(Pair(2, 2)),
        TargetPoint(Pair(5, 5)),
    )
    private val componentViews: MutableList<ComponentView> = components.map { componentToView(it) }.toMutableList()

    private fun componentToView(component: Component): ComponentView {
        return when (component) {
            is Robot -> RobotView(component.location)
            is Blob -> BlobView(component.location)
            is Hazard -> HazardView(component.location)
            is TargetPoint -> TargetView(component.location)
            else -> ComponentView()
        }
    }

    //TODO make change
    fun updateMap(){
        componentViewModel.addComponent(componentToView(Robot(Pair(0, 0))))
    }
    fun uploadMap(){
        print("uploadMap")
        componentViewModel.initComponent(componentViews)
    }
    fun clearMap(){}
    fun updateRobotStatus(status: String){
        componentViewModel.clearComponents()
        robotStatus = status
    }

}

