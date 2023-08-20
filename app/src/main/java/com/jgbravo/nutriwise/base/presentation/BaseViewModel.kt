package com.jgbravo.nutriwise.base.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<STATE: BaseState, EVENT : BaseEvent> : ViewModel() {

    protected abstract val mutableState: MutableStateFlow<STATE>
    abstract val state: StateFlow<STATE>

    abstract fun onEvent(event: EVENT)
}