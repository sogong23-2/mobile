package com.unnamed.mobile.component.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.unnamed.mobile.component.view.ComponentView

class ComponentViewModel: ViewModel() {
    private var components = mutableListOf<ComponentView>()
    val data: State<MutableList<ComponentView>> = mutableStateOf(components)

    fun addComponent(component: ComponentView) {
        components.add(component)
    }
    fun removeComponent(component: ComponentView) {
        components.remove(component)
    }
    fun clearComponents() {
        components.clear()
    }
    fun initComponent(componentViews: MutableList<ComponentView>) {
        for (componentView in componentViews){
            print("log")
            components.add(componentView)
        }
    }
}
