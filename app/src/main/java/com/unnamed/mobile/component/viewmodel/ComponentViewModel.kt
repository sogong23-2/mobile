package com.unnamed.mobile.component.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.unnamed.mobile.component.model.Component
import com.unnamed.mobile.component.model.Robot

class ComponentViewModel : ViewModel() {
    private val _components = mutableStateOf(mutableListOf<Component>())
    private val components: State<MutableList<Component>> = _components

    private val _robot = mutableStateOf(Robot(Pair(0, 0)))
    private val robot: State<Robot> = _robot

    fun addComponent(component: Component) {
        _components.value = (components.value + component) as MutableList<Component>
    }

    fun clearComponents() {
        _components.value = mutableListOf<Component>()
    }

    fun initComponent(componentView: MutableList<Component>) {
        for (componentView in componentView) {
            addComponent(componentView)
        }
        _robot.value = components.value[0] as Robot

    }

    fun moveRobot(next: Pair<Int, Int>) {
        robot.value.moveTo(next)
    }

    fun renderScreen() {
        _components.value = components.value
    }

    fun getComponents(): MutableList<Component> {
        return components.value
    }

}