package com.unnamed.mobile.component.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.unnamed.mobile.component.model.Component
import com.unnamed.mobile.component.model.Dynamic
import com.unnamed.mobile.component.model.Robot

class ComponentViewModel: ViewModel() {
    private var components = mutableListOf<Component>()
    val data: State<MutableList<Component>> = mutableStateOf(components)

    fun addComponent(component: Component) {
        components.add(component)
    }
    fun removeComponent(component: Component) {
        components.remove(component)
    }
    fun clearComponents() {
        components.clear()
    }
    fun initComponent(componentViews: MutableList<Component>) {
        for (componentView in componentViews){
            print("log")
            components.add(componentView)
        }
    }
}