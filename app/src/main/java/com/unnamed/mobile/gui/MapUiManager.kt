package com.unnamed.mobile.gui

import com.unnamed.mobile.model.MapDo
import com.unnamed.mobile.model.model.*
import com.unnamed.mobile.model.ComponentViewModel
import com.unnamed.mobile.model.mapDefault

object MapUiManager {
    //TODO 쓰이는 properties는 다 외부에서 정의할 수 있도록
    private val map = mapDefault
    var mapSize: Pair<Int, Int> = map.mapSize
    val viewModel = ComponentViewModel()

    var robot: Robot = Robot(Pair(map.robot.first.toFloat(), map.robot.second.toFloat()))
    var working: Boolean = true

    private val statics: MutableList<Static> = toStatics(map)


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
        val map = mapDefault
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

    private fun toStatics(map: MapDo): MutableList<Static>{
        val statics = mutableListOf<Static>()
        for(targetPoint in map.targetPoint){
            statics.add(TargetPoint(targetPoint))
        }
        for(blob in map.blob){
            statics.add(Blob(blob))
        }
        for(hazard in map.hazard){
            statics.add(Hazard(hazard))
        }
        for(gray in map.gray){
            statics.add(Gray(gray))
        }
        return statics
    }
}

