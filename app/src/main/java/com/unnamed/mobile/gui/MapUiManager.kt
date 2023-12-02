package com.unnamed.mobile.gui

import com.unnamed.mobile.model.MapDo
import com.unnamed.mobile.model.model.*
import com.unnamed.mobile.model.ComponentViewModel

object MapUiManager {
    //TODO 쓰이는 properties는 다 외부에서 정의할 수 있도록
    var mapSize: Pair<Int, Int> = Pair(5, 5)
    val viewModel = ComponentViewModel()

    var robot: Robot = Robot(Pair(0F, 0F))
    var working: Boolean = true

    private val statics: MutableList<Static> = mutableListOf(
        Blob(Pair(1, 2)),
        Hazard(Pair(1, 1)),
        Blob(Pair(2, 2)),
        TargetPoint(Pair(4, 2)),
        Gray(Pair(4, 0)),
    )


    //TODO make change
    fun updateMap(addingStatics: List<Static>) {
        for(static in addingStatics){
            removeGray(static)
            addComponent(static)
        }
    }

    fun uploadMap() {
        viewModel.clearComponents()
        viewModel.initComponent(statics, robot, mapSize)
        robot.location = viewModel.getRobotLocation()
    }

    fun autoInit(){
        val map = MapDo(
            mapSize = Pair(5, 5),
            robot = Pair(0, 0),
            blob = listOf(Pair(1, 2), Pair(2, 2)),
            hazard = listOf(Pair(1, 1)),
            targetPoint = listOf(Pair(4, 2)),
            gray = listOf(Pair(4, 0))
        )
        initMap(map)
    }

    fun initMap(map: MapDo) {
        mapSize = map.mapSize
        moveLocateInit(pairToFloat(map.robot))
        initStatics(map.blob, map.hazard, map.targetPoint, map.gray)

        uploadMap()
    }

    fun initStatics(
        blobs: List<Pair<Int, Int>>,
        hazards: List<Pair<Int, Int>>,
        targetPoints: List<Pair<Int, Int>>,
        grays: List<Pair<Int, Int>>
    ) {
        statics.clear()

        for (targetPoint in targetPoints) {
            addComponent(toTargetPoint(targetPoint))
        }
        for (blob in blobs) {
            addComponent(toBlob(blob))
        }
        for (hazard in hazards) {
            addComponent(toHazard(hazard))
        }
        for (gray in grays) {
            addComponent(toGray(gray))
        }

    }

    private fun toBlob(location: Pair<Int, Int>): Static{
        return Blob(location)
    }
    private fun toHazard(location: Pair<Int, Int>): Static{
        return Hazard(location)
    }
    private fun toTargetPoint(location: Pair<Int, Int>): Static{
        return TargetPoint(location)
    }
    private fun toGray(location: Pair<Int, Int>): Static{
        return Gray(location)
    }


    private fun addComponent(static: Static) {
        statics.add(static)
        viewModel.addComponent(static)
    }

    private fun removeComponent(static: Static) {
        statics.remove(static)
        viewModel.removeComponent(static)
    }

    private fun removeGray(static: Static){
        val grays = statics.filterIsInstance<Gray>()
        for(gray in grays){
            if(gray.location == static.location){
                removeComponent(gray)
            }
        }
    }

    fun getRobotLocation(): Pair<Float, Float> {
        return robot.location
    }

    fun getStatics(): List<Static> {
        return statics
    }

    suspend fun moveRobot(next: Pair<Int, Int>) {
        viewModel.moveRobotTo(next)
        robot.location = Pair(next.first.toFloat(), next.second.toFloat())
    }

    private fun moveLocateInit(location: Pair<Float, Float>) {
        robot.location = location
    }

    private fun pairToFloat(pair: Pair<Int, Int>): Pair<Float, Float> {
        return Pair(pair.first.toFloat(), pair.second.toFloat())
    }

}

