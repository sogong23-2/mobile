package com.unnamed.mobile.component.view

import com.unnamed.mobile.component.model.*
import com.unnamed.mobile.component.viewmodel.ComponentViewModel

object MapUiManager {
    //TODO 쓰이는 properties는 다 외부에서 정의할 수 있도록
    val mapSize: Pair<Int, Int> = Pair(7, 6)
    var robotStatus: String = "idle"
    val componentViewModel = ComponentViewModel()

    val robot: Robot = Robot(Pair(6F, 5F))

    private val statics: MutableList<Static> = mutableListOf(
        Blob(Pair(0, 0)),
        Hazard(Pair(1, 1)),
        Blob(Pair(2, 2)),
        TargetPoint(Pair(4, 1)),
    )


    //TODO make change
    fun updateMap() {
    }

    fun uploadMap() {
        print("uploadMap")
        componentViewModel.initComponent(statics, robot)
    }
    fun addComponent(static: Static) {
        statics.add(static)
        componentViewModel.addComponent(static)
    }

    fun clearMap() {}
    fun updateRobotStatus(status: String) {
        componentViewModel.clearComponents()
        robotStatus = status
    }
    suspend fun moveRobot(next: Pair<Int, Int>) {
        componentViewModel.moveRobotTo(next)
        robot.location = Pair(next.first.toFloat(), next.second.toFloat())
    }

}

