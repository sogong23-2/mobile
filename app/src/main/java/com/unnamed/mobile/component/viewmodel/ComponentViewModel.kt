package com.unnamed.mobile.component.viewmodel

import android.os.SystemClock.sleep
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.unnamed.mobile.component.model.Dynamic
import com.unnamed.mobile.component.model.Robot
import com.unnamed.mobile.component.model.Static
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

class ComponentViewModel : ViewModel() {
    private val _mapSize = mutableStateOf(Pair(0, 0))
    private val mapSize: State<Pair<Int, Int>> = _mapSize

    private val _statics = mutableStateOf(mutableListOf<Static>())
    private val statics: State<MutableList<Static>> = _statics

    private val _robot = mutableStateOf(Robot(Pair(0F, 0F)))
    private val robot: State<Robot> = _robot

    private val _robotStatus = mutableStateOf("Running")
    private val robotStatus: State<String> = _robotStatus

    fun addComponent(static: Static) {
        _statics.value = (statics.value + static) as MutableList<Static>
    }

    fun clearComponents() {
        _statics.value = mutableListOf<Static>()
        _robot.value = Robot(Pair(0F, 0F))
    }

    fun initComponent(statics: MutableList<Static>, robot: Robot, mapSize: Pair<Int, Int>) {
        for (static in statics) {
            addComponent(static)
        }
        _robot.value = robot
        _mapSize.value = mapSize
    }

    fun getRobotLocation(): Pair<Float, Float> {
        return _robot.value.location
    }
    fun moveRobot(dx: Float, dy: Float) {
        val now = _robot.value.location
        val x = now.first + dx
        val y = now.second + dy
        _robot.value = Robot(Pair(x, y))
    }

    suspend fun moveRobotTo(next: Pair<Int, Int>) {
        val now = _robot.value.location
        val dx = (next.first - now.first) / 100
        val dy = (next.second - now.second) / 100

        coroutineScope {
            for (i in 1..100) {
                moveRobot(dx, dy)
                delay(6)
            }
        }
    }

    fun getStatics(): MutableList<Static> {
        return statics.value
    }

    fun renderDynamics() {
        _robot.value = _robot.value
    }

    fun getDynamics(): MutableList<Dynamic> {
        return mutableListOf(_robot.value)
    }

    fun pauseRobot() {
        _robotStatus.value = "Stopped"
    }
    fun resumeRobot() {
        _robotStatus.value = "Running"
    }
    fun getRobotStatus(): String {
        return _robotStatus.value
    }
    fun getMapSize(): Pair<Int, Int> {
        return mapSize.value
    }
}