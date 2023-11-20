package com.unnamed.mobile.component.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.unnamed.mobile.component.model.Component
import com.unnamed.mobile.component.model.Dynamic
import com.unnamed.mobile.component.model.Robot
import com.unnamed.mobile.component.model.Static

class ComponentViewModel : ViewModel() {
    private val _statics = mutableStateOf(mutableListOf<Static>())
    private val statics: State<MutableList<Static>> = _statics

    private val _robot = mutableStateOf(Robot(Pair(0, 0)))
    private val robot: State<Robot> = _robot

    fun addComponent(static: Static) {
        _statics.value = (statics.value + static) as MutableList<Static>
    }

    fun clearComponents() {
        _statics.value = mutableListOf<Static>()
        _robot.value = Robot(Pair(0, 0))
    }

    fun initComponent(statics: MutableList<Static>, robot: Robot) {
        for (static in statics) {
            addComponent(static)
        }
        _robot.value = robot

    }

    fun moveRobot(next: Pair<Int, Int>) {
//        _robot.value = robot.value.apply {
//            moveTo(next)
//        }
        _robot.value = Robot(next)
    }

    fun getStatics(): MutableList<Static> {
        return statics.value
    }

    fun renderDynamics() {
        _robot.value = _robot.value
    }

    fun getDynamics(): MutableList<Dynamic> {
        return mutableListOf(robot.value)
    }

}